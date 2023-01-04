import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends GLCanvas implements GLEventListener, KeyListener {

    private int textureSpaceship;
    private int textureEnemy;
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final Spaceship spaceShip = new Spaceship();
    private boolean shoot = false;

    public Game() {
        this.setPreferredSize(new Dimension(1400, 800));

        final FPSAnimator animator = new FPSAnimator(this, 300,true );

        final JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setTitle("Shoot Dat Block");
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);
        this.addGLEventListener(this);
        animator.start();
    }

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        for (int i = 0; i < 3; i++) {
            for (int j = -3; j < 3; j++) {
                enemies.add(new Enemy(j * 2.0f, 1.0f + i * 2.0f));
            }
        }

        try {
            File im1 = new File("textures/shooter.png");
            Texture t1 = TextureIO.newTexture(im1, true);
            textureSpaceship = t1.getTextureObject(gl);

            File im2 = new File("textures/enemy.jpg");
            Texture t2 = TextureIO.newTexture(im2, true);
            textureEnemy = t2.getTextureObject(gl);

            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
            gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);

            gl.glEnable(GL2.GL_TEXTURE_2D);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        for (Enemy enemy : enemies) {
            gl.glPushMatrix();
            enemy.draw(gl, textureEnemy);
            gl.glPopMatrix();
        }

        gl.glPushMatrix();
        spaceShip.draw(gl, textureSpaceship);
        gl.glPopMatrix();

        gl.glPushMatrix();
        Projectile shot = spaceShip.getProjectile();

        if (shoot && shot == null) {
            spaceShip.shoot(gl);
        } else if (shot != null) {
            shot.draw(gl);
            shot.move();

            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                if (shot.detectCollision(enemy)) {
                    enemies.remove(i);
                    spaceShip.removeProjectile();
                    break;
                }
            }

            if (shot.getY() > 6f) {
                spaceShip.removeProjectile();
            }
        }
        gl.glPopMatrix();

        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int width, int height) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        GLU glu = GLU.createGLU(gl);

        float aspect = (float)width / height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, aspect, 0.1, 100.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(spaceShip.getX() > -9.6) {
                spaceShip.move(false);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(spaceShip.getX() < 9.6) {
                spaceShip.move(true);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shoot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shoot = false;
        }
    }
}

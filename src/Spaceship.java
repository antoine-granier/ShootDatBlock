import com.jogamp.opengl.GL2;

public class Spaceship {

    private static final float SPEED = 0.4f;
    private float x;
    private Projectile shot;

    public Spaceship() {
        this.x = 0;
        this.shot = null;
    }

    public void draw(GL2 gl, int texture) {
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);

        gl.glTranslatef(x, -5.0f, -15.0f);
        gl.glScalef(1f, 1f, 1f);
        gl.glBegin(GL2.GL_QUADS);

        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.4f, -0.4f,  0.4f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.4f, -0.4f,  0.4f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 0.4f,  0.4f,  0.4f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.4f,  0.4f,  0.4f);

        gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glColor3d(0, 0, 0);

        gl.glVertex3f(-0.4f, -0.4f, -0.4f);
        gl.glVertex3f(-0.4f,  0.4f, -0.4f);
        gl.glVertex3f( 0.4f,  0.4f, -0.4f);
        gl.glVertex3f( 0.4f, -0.4f, -0.4f);

        gl.glVertex3f(-0.4f,  0.4f, -0.4f);
        gl.glVertex3f(-0.4f,  0.4f,  0.4f);
        gl.glVertex3f( 0.4f,  0.4f,  0.4f);
        gl.glVertex3f( 0.4f,  0.4f, -0.4f);

        gl.glVertex3f(-0.4f, -0.4f, -0.4f);
        gl.glVertex3f( 0.4f, -0.4f, -0.4f);
        gl.glVertex3f( 0.4f, -0.4f,  0.4f);
        gl.glVertex3f(-0.4f, -0.4f,  0.4f);

        gl.glVertex3f( 0.4f, -0.4f, -0.4f);
        gl.glVertex3f( 0.4f,  0.4f, -0.4f);
        gl.glVertex3f( 0.4f,  0.4f,  0.4f);
        gl.glVertex3f( 0.4f, -0.4f,  0.4f);

        gl.glVertex3f(-0.4f, -0.4f, -0.4f);
        gl.glVertex3f(-0.4f, -0.4f,  0.4f);
        gl.glVertex3f(-0.4f,  0.4f,  0.4f);
        gl.glVertex3f(-0.4f,  0.4f, -0.4f);
        gl.glEnd();
    }

    public void move(boolean right) {
        if (right) {
            x += SPEED;
        } else {
            x -= SPEED;
        }
    }

    public void shoot(GL2 gl) {
        if (shot == null) {
            shot = new Projectile(x, -5.0f);
        }
        shot.draw(gl);
    }

    public void removeProjectile() {
        shot = null;
    }

    public float getX() {
        return x;
    }

    public Projectile getProjectile() {
        return shot;
    }
}

import com.jogamp.opengl.GL2;

public class Projectile {

    private static final float SPEED = 0.2f;
    private final float x;
    private float y;

    public Projectile(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void draw(GL2 gl) {
        gl.glTranslatef(x, y, -15.0f);
        gl.glScalef(1f, 1f, 1f);
        gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glColor3f(0, 0, 1);

        gl.glBegin(GL2.GL_QUADS);

        gl.glVertex3f(-0.1f, 0.2f, 0.2f);
        gl.glVertex3f(0.1f, 0.2f, 0.2f);
        gl.glVertex3f(0.1f, -0.2f, 0.2f);
        gl.glVertex3f(-0.1f, -0.2f, 0.2f);

        gl.glVertex3f(-0.1f, 0.2f, -0.2f);
        gl.glVertex3f(0.1f, 0.2f, -0.2f);
        gl.glVertex3f(0.1f, -0.2f, -0.2f);
        gl.glVertex3f(-0.1f, -0.2f, -0.2f);

        gl.glVertex3f(-0.1f, 0.2f, -0.2f);
        gl.glVertex3f(0.1f, 0.2f, -0.2f);
        gl.glVertex3f(0.1f, 0.2f, 0.2f);
        gl.glVertex3f(-0.1f, 0.2f, 0.2f);

        gl.glVertex3f(-0.1f, -0.2f, -0.2f);
        gl.glVertex3f(0.1f, -0.2f, -0.2f);
        gl.glVertex3f(0.1f, -0.2f, 0.2f);
        gl.glVertex3f(-0.1f, -0.2f, 0.2f);

        gl.glVertex3f(-0.1f, 0.2f, 0.2f);
        gl.glVertex3f(-0.1f, 0.2f, -0.2f);
        gl.glVertex3f(-0.1f, -0.2f, -0.2f);
        gl.glVertex3f(-0.1f, -0.2f, 0.2f);

        gl.glVertex3f(0.1f, 0.2f, 0.2f);
        gl.glVertex3f(0.1f, 0.2f, -0.2f);
        gl.glVertex3f(0.1f, -0.2f, -0.2f);
        gl.glVertex3f(0.1f, -0.2f, 0.2f);

        gl.glEnd();
        gl.glEnable(GL2.GL_TEXTURE_2D);
    }

    public void move() {
        y += SPEED;
    }

    public boolean detectCollision(Enemy enemy) {
        return x >= enemy.getX() - 0.4f && x <= enemy.getX() + 0.4f && y >= enemy.getY() - 0.4f && y <= enemy.getY() + 0.4f;
    }

    public float getY() {
        return y;
    }
}

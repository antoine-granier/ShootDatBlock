import com.jogamp.opengl.GL2;

public class Enemy {

    private static final float SPEED = 0.3f;
    private final float x;
    private final float y;

    public Enemy(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void draw(GL2 gl, int texture) {
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        gl.glTranslatef(x, y, -15.0f);
        gl.glScalef(1f, 1f, 1f);

        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-0.4f, -0.4f,  0.4f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 0.4f, -0.4f,  0.4f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 0.4f,  0.4f,  0.4f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-0.4f,  0.4f,  0.4f);

        gl.glVertex3f(-0.4f, -0.4f, -0.4f);
        gl.glVertex3f(0.4f, -0.4f, -0.4f);
        gl.glVertex3f(0.4f, 0.4f, -0.4f);
        gl.glVertex3f(-0.4f, 0.4f, -0.4f);

        gl.glVertex3f(-0.4f, -0.4f, 0.4f);
        gl.glVertex3f(0.4f, -0.4f, 0.4f);
        gl.glVertex3f(0.4f, -0.4f, -0.4f);
        gl.glVertex3f(-0.4f, -0.4f, -0.4f);

        gl.glVertex3f(-0.4f, 0.4f, 0.4f);
        gl.glVertex3f(0.4f, 0.4f, 0.4f);
        gl.glVertex3f(0.4f, 0.4f, -0.4f);
        gl.glVertex3f(-0.4f, 0.4f, -0.4f);

        gl.glVertex3f(-0.4f, -0.4f, 0.4f);
        gl.glVertex3f(-0.4f, 0.4f, 0.4f);
        gl.glVertex3f(-0.4f, 0.4f, -0.4f);
        gl.glVertex3f(-0.4f, -0.4f, -0.4f);

        gl.glVertex3f(0.4f, -0.4f, 0.4f);
        gl.glVertex3f(0.4f, 0.4f, 0.4f);
        gl.glVertex3f(0.4f, 0.4f, -0.4f);
        gl.glVertex3f(0.4f, -0.4f, -0.4f);
        gl.glEnd();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

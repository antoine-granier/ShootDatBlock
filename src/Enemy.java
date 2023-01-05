import com.jogamp.opengl.GL2;

public class Enemy {

    private static final float SPEED = 0.05f;
    private float x;
    private final float y;
    private static final float RIGHT_LIMIT = 10.0f;
    private static final float LEFT_LIMIT = -10.0f;
    private boolean right = true;

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

    public void move() {
        if(right && x < RIGHT_LIMIT) {
            x += SPEED;
        }
        if(right && x >= RIGHT_LIMIT) {
            right = false;
        }
        if(!right && x > LEFT_LIMIT) {
            x -= SPEED;
        }
        if(!right && x <= LEFT_LIMIT) {
            right = true;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

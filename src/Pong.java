import java.awt.Color;

public class Pong
{
    public static final int FIELD_WIDTH = 800;
    public static final int FIELD_HEIGHT = 600;
    public static final String PLAYER1 = "Player 1";
    public static final String PLAYER2 = "Player 2";
    public static final String TOP = "Top";
    public static final String BOTTOM = "Bottom";
    public static final String LEFT = "Left";
    public static final String RIGHT = "Right";
    
    private static void busyWait(final long n) {
        while (System.currentTimeMillis() < n) {
            Thread.yield();
        }
    }
    
    private static Wall[] initWalls() {
        return new Wall[] { new Wall(10.0, 350.0, 20.0, 500.0, Color.WHITE, "Left"), new Wall(790.0, 350.0, 20.0, 500.0, Color.WHITE, "Right"), new Wall(400.0, 110.0, 800.0, 20.0, Color.WHITE, "Top"), new Wall(400.0, 590.0, 800.0, 20.0, Color.WHITE, "Bottom") };
    }
    
    private static Player[] initPlayers() {
        final Player[] array = new Player[2];
        final double[] array2 = { 120.0, 580.0 };
        array[0] = new Player(80.0, 300.0, 20.0, 100.0, Color.GREEN, "Player 1", array2, 0.5);
        array[1] = new Player(720.0, 300.0, 20.0, 100.0, Color.BLUE, "Player 2", array2, 0.5);
        return array;
    }
    
    private static Score[] initScores() {
        return new Score[] { new Score("Player 1"), new Score("Player 2") };
    }
    
    public static void main(final String[] array) {
        final long n = (array.length > 0) ? Long.parseLong(array[0]) : 5L;
        final boolean b = array.length > 1 && Boolean.parseBoolean(array[1]);
        final boolean b2 = true;
        boolean b3 = false;
        System.out.println("safe_mode = " + b);
        if (b) {
            GameLib.initGraphics_SAFE_MODE("Pong!", 800, 600);
        }
        else {
            GameLib.initGraphics("Pong!", 800, 600);
        }
        final Ball ball = new Ball(400.0, 300.0, 20.0, 20.0, Color.YELLOW, 0.65);
        final Wall[] initWalls = initWalls();
        final Player[] initPlayers = initPlayers();
        final Score[] initScores = initScores();
        long currentTimeMillis = System.currentTimeMillis();
        while (b2) {
            final long currentTimeMillis2 = System.currentTimeMillis();
            if (GameLib.isKeyPressed(10)) {
                b3 = true;
            }
            if (b3) {
                ball.update(currentTimeMillis2 - currentTimeMillis);
                if (GameLib.isKeyPressed(6)) {
                    initPlayers[0].moveUp(currentTimeMillis2 - currentTimeMillis);
                }
                if (GameLib.isKeyPressed(7)) {
                    initPlayers[0].moveDown(currentTimeMillis2 - currentTimeMillis);
                }
                if (GameLib.isKeyPressed(8)) {
                    initPlayers[1].moveUp(currentTimeMillis2 - currentTimeMillis);
                }
                if (GameLib.isKeyPressed(9)) {
                    initPlayers[1].moveDown(currentTimeMillis2 - currentTimeMillis);
                }
                for (final Player player : initPlayers) {
                    if (ball.checkCollision(player)) {
                        ball.onPlayerCollision(player.getId());
                        break;
                    }
                }
                for (final Wall wall : initWalls) {
                    if (ball.checkCollision(wall)) {
                        if (wall.getId().equals("Left")) {
                            initScores[1].inc();
                        }
                        if (wall.getId().equals("Right")) {
                            initScores[0].inc();
                        }
                        ball.onWallCollision(wall.getId());
                        break;
                    }
                }
            }
            else {
                if (System.currentTimeMillis() / 500L % 2L == 0L) {
                    GameLib.setColor(Color.YELLOW);
                    GameLib.drawText("Pressione <ESPA\u00c7O> para come\u00e7ar", 480.0, 2);
                }
                GameLib.setColor(Color.GREEN);
                GameLib.drawText("Teclas A/Z: move o jogador da esquerda", 520.0, 2);
                GameLib.setColor(Color.BLUE);
                GameLib.drawText("Telas K/M: move o jogador da direita", 560.0, 2);
            }
            GameLib.setColor(Color.YELLOW);
            GameLib.drawText("Pong!", 70.0, 2);
            ball.draw();
            final Wall[] array4 = initWalls;
            for (int length3 = array4.length, k = 0; k < length3; ++k) {
                array4[k].draw();
            }
            final Player[] array5 = initPlayers;
            for (int length4 = array5.length, l = 0; l < length4; ++l) {
                array5[l].draw();
            }
            final Score[] array6 = initScores;
            for (int length5 = array6.length, n2 = 0; n2 < length5; ++n2) {
                array6[n2].draw();
            }
            GameLib.display();
            busyWait(currentTimeMillis2 + n);
            currentTimeMillis = currentTimeMillis2;
        }
    }
}
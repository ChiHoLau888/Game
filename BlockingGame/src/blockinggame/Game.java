/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockinggame;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author abina
 */
public class Game {

    /*Width of screen */
    public final static int SCREENWIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    /*Height of screen*/
    public final static int SCREENHEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    /*Standard size of enemy, used for width and height*/
    public final static int ENEMYSIZE = 80;

    EntityBase floor;
    Enemy leftEnemy;
    Enemy rightEnemy;
    Enemy topEnemy;
    Player player;
    ArrayList<Enemy> enemy = new ArrayList();
    ArrayList<Projectile> projectile = new ArrayList();
    Group root = new Group();
    Scene scene = new Scene(root, SCREENWIDTH, SCREENHEIGHT);
    Timeline timeline1 = new Timeline();
    int timeSeconds = 0;
    int attackSeconds = 0;

    /*
     * Constructor for game class, creates all entities
     */
    public Game(Stage primaryStage) {
        createEntities();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        //GameLoop();
        scene.setOnKeyPressed((KeyEvent e) -> {
            KeyCode code = e.getCode();
            player.setBlockingStatus(code);

        });
        scene.setOnKeyReleased((KeyEvent e) -> {
            KeyCode code = e.getCode();

        });
        createMainTimeline();
        GameLoop();
        newGameLoop();
    }

    private void GameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //leftEnemy.setEntityXPosition(leftEnemy.getxPosition()+1);
                //System.out.println(leftEnemy.getxPosition());
                //System.out.println(leftEnemy.getyPosition());
                moveProjectile();
                }
                private void moveProjectile() {
                    for (Projectile projectile : projectile) {
                        switch (projectile.getDirection()) {
                            case 0:
                                projectile.setEntityYPosition(projectile.getyPosition() + projectile.getDy());
                                break;
                            case 1:
                                projectile.setEntityXPosition(projectile.getxPosition() + projectile.getDx());
                                break;
                            case 2:
                                projectile.setEntityXPosition(projectile.getxPosition() - projectile.getDx());
                                break;
                            default:
                                break;
                        }
            }
                Projectile removed = null;
                for (Projectile projectile1 : projectile) {
                    boolean hit = false;
                    if (projectile1.getEntity().intersects(player.getEntity().getBoundsInLocal())) {
                        hit = true;
                        root.getChildren().remove(projectile1.getEntity());
                        removed=projectile1;
                        //projectile.remove(projectile1);
                    }
                    //System.out.println("a");
                    if (hit) {
                        if (player.isBlocking() && player.getBlockDirection() == projectile1.getDirection()) {
                        } else {
                            player.setLives(player.getLives() - 1);
                        }
                        hit = false;
                        
                    }
                    
                }
                projectile.remove(removed);
            }
        }.start();

    }

    private int createRandNum() {
        Random rand = new Random();
        int randNum = rand.nextInt(4);
        return randNum;
    }

    /*
     * Creates all main entities that will appear on screen
     */
    private void createEntities() {
        floor = new EntityBase(SCREENWIDTH, 250, 0, (SCREENHEIGHT - 250));
        leftEnemy = new Enemy(ENEMYSIZE, ENEMYSIZE, 50, SCREENHEIGHT - 350);
        rightEnemy = new Enemy(ENEMYSIZE, ENEMYSIZE, SCREENWIDTH - 130, SCREENHEIGHT - 350);
        topEnemy = new Enemy(ENEMYSIZE, ENEMYSIZE, (SCREENWIDTH / 2) - (ENEMYSIZE / 2), 50);
        enemy.add(topEnemy);
        enemy.add(leftEnemy);
        enemy.add(rightEnemy);
        player = new Player(100, 200, (SCREENWIDTH / 2) - (100 / 2), (SCREENHEIGHT - 450));
        for (int i = 0; i < enemy.size(); i++) {
            root.getChildren().add(enemy.get(i).getEntity());
        }
        root.getChildren().add(player.getEntity());
        root.getChildren().add(floor.getEntity());
    }

    private void newGameLoop() {
        timeline1.playFromStart();
    }

    private void createMainTimeline() {
        timeline1.setCycleCount(30);
        timeline1.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler() {
                    boolean attackCheck = false;
                    int direction;

                    @Override
                    public void handle(Event event) {
                        System.out.println("Player lives " + player.getLives());
                if (player.getLives() <= 0) {
                    System.out.println("Lost");
                }
                        timeSeconds++;
                        System.out.println(timeSeconds);
                        System.out.println("Direction+" + direction);
                        if (attackCheck) {
                            attackSeconds++;
                        }
                        if (attackSeconds == 1) {
                            switch (direction) {
                                case 0:
                                    //top
                                    Projectile projectile1 = new Projectile(ENEMYSIZE / 2, ENEMYSIZE / 2, (SCREENWIDTH / 2) - (ENEMYSIZE / 4), 90, 0);
                                    root.getChildren().add(projectile1.getEntity());
                                    projectile.add(projectile1);
                                    break;
                                case 1:
                                    //left
                                    Projectile projectile2 = new Projectile(ENEMYSIZE / 2, ENEMYSIZE / 2, 90, SCREENHEIGHT - 330, 1);
                                    root.getChildren().add(projectile2.getEntity());
                                    projectile.add(projectile2);
                                    break;
                                case 2:
                                    //right
                                    Projectile projectile3 = new Projectile(ENEMYSIZE / 2, ENEMYSIZE / 2, SCREENWIDTH - 130, SCREENHEIGHT - 330, 2);
                                    root.getChildren().add(projectile3.getEntity());
                                    projectile.add(projectile3);
                                    break;
                                default:
                                    break;
                            }
                            attackSeconds = 0;
                            attackCheck = false;
                        }
                        checkTime();
                    }

                    private void checkTime() {
                        if ((timeSeconds / 2) * 2 == timeSeconds) {
                            attackCheck = true;
                            direction = createRandNum();
                            changeOpacity(direction, true);
                        } else {
                            changeOpacity(direction, false);
                        }
                        if (timeSeconds == 30) {
                            System.out.println("a");
                        }
                    }

                    private void changeOpacity(int direction, boolean b) {
                        switch (direction) {
                            case 0:
                                if (b) {
                                    topEnemy.changeOpacity(true);
                                } else {
                                    topEnemy.changeOpacity(false);
                                }
                                break;
                            case 1:
                                if (b) {
                                    leftEnemy.changeOpacity(true);
                                } else {
                                    leftEnemy.changeOpacity(false);
                                }
                                break;
                            case 2:
                                if (b) {
                                    rightEnemy.changeOpacity(true);
                                } else {
                                    rightEnemy.changeOpacity(false);
                                }
                                break;
                            default:
                                break;
                        }

                    }
                }));
    }
}

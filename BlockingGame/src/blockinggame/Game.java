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
import javafx.scene.control.Label;
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
    public final static int THIRTY = 30;
    EntityBase floor;
    Enemy leftEnemy;
    Enemy rightEnemy;
    Enemy topEnemy;
    Player player;
    ArrayList<Enemy> enemy = new ArrayList();
    ArrayList<Projectile> projectile = new ArrayList();
    Group root = new Group();
    Scene scene = new Scene(root, SCREENWIDTH, SCREENHEIGHT);
    Timeline gameTimeline = new Timeline();
    Timeline timerTimeline = new Timeline();
    int timeSeconds = 0;
    int currentTime = 0;
    int attackTime = 0;
    boolean gamePaused = true;
    Stage primaryStage;
    Label livesLabel = new Label();
    Label timeLabel = new Label();

    public Group getRoot() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }

    /*
     * Constructor for game class, creates all entities
     */
    public Game(Stage primaryStage) {
        this.primaryStage = primaryStage;
        createEntities();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.toFront();
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        //GameLoop();
        scene.setOnKeyPressed((KeyEvent e) -> {
            KeyCode code = e.getCode();
            player.setBlockingStatus(code);

        });
        scene.setOnKeyReleased((KeyEvent e) -> {
            KeyCode code = e.getCode();

        });
        startGameLoop();
    }

    private void GameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                //leftEnemy.setEntityXPosition(leftEnemy.getxPosition()+1);
                //System.out.println(leftEnemy.getxPosition());
                //System.out.println(leftEnemy.getyPosition());
                moveProjectile();
                checkProjectileHit();
                livesLabel.setText("Lives:" + player.getLives());
                root.getChildren().remove(livesLabel);
                root.getChildren().add(livesLabel);
                if (player.getLives() == 0) {
                    stop();
                    gameTimeline.stop();
                    timerTimeline.stop();
                }
                if (timeSeconds == 30) {
                    stop();
                    gameTimeline.stop();
                    timerTimeline.stop();
                }

            }

        }.start();
    }

    private void checkProjectileHit() {
        Projectile removed = null;
        for (Projectile projectile1 : projectile) {
            boolean hit = false;
            if (projectile1.getEntity().intersects(player.getEntity().getBoundsInLocal())) {
                hit = true;
                root.getChildren().remove(projectile1.getEntity());
                removed = projectile1;
                if (hit) {
                    if (player.isBlocking() && player.getBlockDirection() == projectile1.getDirection()) {
                    } else {
                        player.setLives(player.getLives() - 1);
                    }
                    hit = false;

                }
                player.stopBlockTimer();
            }

        }
        projectile.remove(removed);
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
    }

    private int createRandNum() {
        Random rand = new Random();
        int randNum = rand.nextInt(16);
        return randNum;
    }

    /*
     * Creates all main entities that will appear on screen
     */
    private void createEntities() {
        //Main entities
        floor = new EntityBase(SCREENWIDTH, 250, 0, (SCREENHEIGHT - 250));
        leftEnemy = new Enemy(ENEMYSIZE, ENEMYSIZE, 50, SCREENHEIGHT - 350);
        rightEnemy = new Enemy(ENEMYSIZE, ENEMYSIZE, SCREENWIDTH - 130, SCREENHEIGHT - 350);
        topEnemy = new Enemy(ENEMYSIZE, ENEMYSIZE, (SCREENWIDTH / 2) - (ENEMYSIZE / 2), 50);
        enemy.add(topEnemy);
        enemy.add(leftEnemy);
        enemy.add(rightEnemy);
        player = new Player(100, 200, (SCREENWIDTH / 2) - (100 / 2), (SCREENHEIGHT - 450));
        //Text
        livesLabel.setText("Lives:" + player.getLives());
        livesLabel.setLayoutX(SCREENWIDTH - 200);
        timeLabel.setText("Time Remaining: " + 30);
        timeLabel.setLayoutX(SCREENWIDTH - 200);
        timeLabel.setLayoutY(15);
        //Added to scene
        root.getChildren().add(livesLabel);
        root.getChildren().add(timeLabel);
        root.getChildren().add(player.getEntity());
        root.getChildren().add(floor.getEntity());
        for (int i = 0; i < enemy.size(); i++) {
            root.getChildren().add(enemy.get(i).getEntity());
        }
    }

    private void startGameLoop() {
        createMainTimeline();
        createTimerTimeline();
        GameLoop();
        gameTimeline.playFromStart();
        timerTimeline.playFromStart();
    }

    private void createMainTimeline() {
        gameTimeline.setCycleCount(30);
        gameTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new EventHandler() {
            boolean attackCheck = false;
            int direction;

            @Override
            public void handle(Event event) {
                System.out.println("Player lives " + player.getLives());
                if (player.getLives() <= 0) {
                    System.out.println("Lost");
                }
                currentTime++;
                checkTime();
            }

            private void checkTime() {
                System.out.println(currentTime);
                System.out.println("Direction+" + direction);
                if (attackCheck) {
                    attackTime++;
                }
                createProjectiles();
                createDirection();
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

            private void createProjectiles() {
                if (attackTime == 1) {
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
                    attackTime = 0;
                    attackCheck = false;
                }
            }

            private void createDirection() {
                if ((currentTime / 2) * 2 == currentTime) {
                    attackCheck = true;

                    int tempDirection = createRandNum();
                    if (tempDirection >= 0 && tempDirection < 5) {
                        direction = 0;
                    } else if (tempDirection >= 5 && tempDirection < 10) {
                        direction = 1;
                    } else if (tempDirection >= 10 && tempDirection < 15) {
                        direction = 2;
                    } else {
                        direction = 3;
                    }
                    changeOpacity(direction, true);
                } else {
                    changeOpacity(direction, false);
                }
            }

        }));
    }

    private void createTimerTimeline() {
        timerTimeline.setCycleCount(THIRTY);
        timerTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new EventHandler() {
            @Override
            public void handle(Event event) {
                timeSeconds++;
                timeLabel.setText("Time Remaining:" + (30 - timeSeconds));
                root.getChildren().remove(timeLabel);
                root.getChildren().add(timeLabel);
            }
        }));
    }
}

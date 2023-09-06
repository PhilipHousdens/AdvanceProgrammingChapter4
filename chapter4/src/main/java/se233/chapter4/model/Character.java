package se233.chapter4.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import se233.chapter4.Launcher;
import se233.chapter4.view.Platform;

//Imports are omitted
public class Character extends Pane {
    private static final Logger logger = LogManager.getLogger(Character.class);
    private int characterWidth;
    private int characterHeight;
    private Image characterImg;
    private AnimatedSprite imageView;
    private int x;
    private int y;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    int xVelocity = 0;
    int yVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity ;
    int yMaxVelocity ;
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;
    public Character(int x, int y, int offsetX, int offsetY, KeyCode leftKey,
                     KeyCode rightKey, KeyCode upKey,int xMaxVelocity,int yMaxVelocity,String skin,int sprCount,int sprColumn,int sprRow ,int sprWidth,int sprHeight,
                     int characterWidth, int characterHeight) {
        this.x = x;
        this.y = y;
        this.xMaxVelocity = xMaxVelocity;
        this.yMaxVelocity = yMaxVelocity;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.characterImg = new Image(Launcher.class.getResourceAsStream(skin));
        this.imageView = new AnimatedSprite(characterImg,sprCount,sprColumn,sprRow,offsetX,offsetY,sprWidth,sprHeight);
        this.characterWidth = characterWidth;
        this.characterHeight = characterHeight;
        this.imageView.setFitWidth(this.characterWidth);
        this.imageView.setFitHeight(this.characterHeight);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
         }
    public void moveLeft() {
        isMoveLeft = true;
        isMoveRight = false;
    }
    public void moveRight() {
        isMoveLeft = false;
        isMoveRight = true;
    }
    public void stop() {
        isMoveLeft = false;
        isMoveRight = false;
    }
    public void moveX() {
        setTranslateX(x);
        if(isMoveLeft) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x - xVelocity; }
        if(isMoveRight) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x + xVelocity; }
    }
    public void moveY() {
        setTranslateY(y);
        if(isFalling) {
            yVelocity = yVelocity >= yMaxVelocity? yMaxVelocity : yVelocity+yAcceleration;
            y = y + yVelocity;
        } else if(isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity-yAcceleration;
            y = y - yVelocity;
        }
    }
    public Image getCharacterImg() {
        return characterImg;
    }
    public void setCharacterImg(Image characterImg) {
        this.characterImg = characterImg;
    }
    public AnimatedSprite getImageView() {
        return imageView;
    } //EX2 Ex3
    public void setImageView(AnimatedSprite imageView) {
        this.imageView = imageView;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public KeyCode getLeftKey() {
        return leftKey;
    }
    public void setLeftKey(KeyCode leftKey) {
        this.leftKey = leftKey;
    }
    public KeyCode getRightKey() {
        return rightKey;
    }
    public void setRightKey(KeyCode rightKey) {
        this.rightKey = rightKey;
    }
    public KeyCode getUpKey() {
        return upKey;
    }
    public void setUpKey(KeyCode upKey) {
        this.upKey = upKey;
    }
    public int getxVelocity() {
        return xVelocity;
    }
    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }
    public int getyVelocity() {
        return yVelocity;
    }
    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }
    public int getxAcceleration() {
        return xAcceleration;
    }
    public void setxAcceleration(int xAcceleration) {
        this.xAcceleration = xAcceleration;
    }
    public int getyAcceleration() {
        return yAcceleration;
    }
    public void setyAcceleration(int yAcceleration) {
        this.yAcceleration = yAcceleration;
    }
    public int getxMaxVelocity() {
        return xMaxVelocity;
    }
    public void setxMaxVelocity(int xMaxVelocity) {
        this.xMaxVelocity = xMaxVelocity;
    }
    public int getyMaxVelocity() {
        return yMaxVelocity;
    }
    public void setyMaxVelocity(int yMaxVelocity) {
        this.yMaxVelocity = yMaxVelocity;
    }
    public boolean isMoveLeft() {
        return isMoveLeft;
    }
    public void setMoveLeft(boolean moveLeft) {
        isMoveLeft = moveLeft;
    }
    public boolean isMoveRight() {
        return isMoveRight;
    }
    public void setMoveRight(boolean moveRight) {
        isMoveRight = moveRight;
    }
    public boolean isFalling() {
        return isFalling;
    }
    public void setFalling(boolean falling) {
        isFalling = falling;
    }
    public boolean isCanJump() {
        return canJump;
    }
    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }
    public boolean isJumping() {
        return isJumping;
    }
    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
    public void checkReachGameWall() {
        if(x <= 0) {
            x = 0;
        } else if( x + characterWidth >= Platform.WIDTH) {  // Using characterWidth here
            x = Platform.WIDTH - characterWidth;
        }
    }
    public void jump() {
        if (canJump) {
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }
    public void checkReachHighest () {
        if(isJumping && yVelocity <= 0) {

            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }
    public void checkReachFloor() {
        if(isFalling && y >= Platform.GROUND - characterHeight) {  // Using characterHeight here
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }
    public void repaint() {
        moveX();
        moveY();
    }
    int linecount = 0;
    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}",x,y,xVelocity,yVelocity,linecount++);
    }
}


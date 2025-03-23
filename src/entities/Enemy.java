package entities;

import main.Game;

import static utils.Constants.EnemyConstants.*;
import static utils.HelpMethods.*;
import static utils.Constants.Directions.*;

public abstract class Enemy extends Entity {
    private int animationIndex, enemyState, enemyType;
    private int animationTick, animationSpeed = 25;
    private boolean firstUpdate = true;
    private boolean inAir;
    private float fallSpeed;
    private float gravity = 0.04f * Game.SCALE;
    private float walkSpeed = 0.5f * Game.SCALE;
    private int walkDir = LEFT;


    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyState = enemyType;
        initHitbox(x, y, width, height);
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(enemyType, enemyState)) {
                animationIndex = 0;
            }
        }
    }

    private void updateMove(int[][] lvlData) {
        if (firstUpdate) {
            if (!IsEntityOnFloor(hitBox, lvlData)) {
                inAir = true;
            }
            firstUpdate = false;
        }

        if (inAir) {
            if (CanMoveHere(hitBox.x, hitBox.y + fallSpeed, hitBox.width, hitBox.height, lvlData)) {
                hitBox.y += fallSpeed;
                fallSpeed += gravity;
            } else {
                inAir = false;
                hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, fallSpeed);
            }
        } else {
            switch (enemyState) {
                case IDLE:
                    enemyState = RUNNING;
                    break;
                case RUNNING:
                    float xSpeed = 0;
                    if (walkDir == LEFT)
                        xSpeed = -walkSpeed;
                    else
                        xSpeed = walkSpeed;

                    if (CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
                        if (IsFloor(hitBox, xSpeed,lvlData)) {
                            hitBox.x += xSpeed;
                            return;
                        }
                    }
                    changeWalkDir();

                    break;
            }
        }
    }

    private void changeWalkDir() {
        if (walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }

    public void update(int[][] lvlData) {
        updateMove(lvlData);
        updateAnimationTick();

    }

    public int getAnimationIndex() {
        return animationIndex;
    }

    public void setAnimationIndex(int animationIndex) {
        this.animationIndex = animationIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }

    public void setEnemyState(int enemyState) {
        this.enemyState = enemyState;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(int enemyType) {
        this.enemyType = enemyType;
    }

    public int getAnimationTick() {
        return animationTick;
    }

    public void setAnimationTick(int animationTick) {
        this.animationTick = animationTick;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }
}

package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.SubClass.Constant;
import uet.oop.bomberman.graphics.Sprite;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.entities.SubClass.Constant.score;

public class Alien extends DynamicEntity {
    protected double speed = 2;
    private final int MOVE_NO_COLLISION = 1;
    private final int MOVE_WITH_COLLISION = -1;
    public Alien(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public void update() {

    }

   private int randomDirection() {
       return (int) (Math.random() * (Constant.STATUS_LEFT - Constant.STATUS_UP + 1) + Constant.STATUS_UP);
   }

    protected void autoMove() {
        if(status == Constant.STATUS_STAND) {
            status = randomDirection();
        }
        move(MOVE_NO_COLLISION);
        if(checkCollision() != Constant.NO_COLLISION) {
            move(MOVE_WITH_COLLISION);
            int tempStatus = status;
            while (true) {
                tempStatus = randomDirection();
                if(tempStatus != status) {
                    break;
                }
            }
            status = tempStatus;
        }
        if(checkCollision() == Constant.COLLISION_WITH_FLAME) {
            status = Constant.STATUS_DESTROY;
            if (this instanceof Oneal) {
                Constant.score++;
            } else {
                Constant.score+=2;
            }

        }

    }

    private void move(int type) {
        if(status == Constant.STATUS_UP) {
            y -= type * speed;
        } else if(status == Constant.STATUS_RIGHT) {
            x += type * speed;
        } else if(status == Constant.STATUS_DOWN) {
            y += type * speed;
        } else if (status == Constant.STATUS_LEFT) {
            x -= type * speed;
        }
    }

}

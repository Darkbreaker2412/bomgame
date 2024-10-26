package entity;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;



import main.GamePanel;
import entity.Player;


import static MenuSetUp.DimensionSize.tileSize;



import java.awt.Color;



import java.util.Random;

public class Monster extends Entity {
    //private BufferedImage monsterImage;
    private GamePanel gp;
    private Random random;
    BufferedImage[] monsterUp, monsterDown, monsterLeft, monsterRight;
    
    public Monster(GamePanel gp, int x, int y) {
        this.gp = gp;
        this.name = "monster";
        this.x = x;
        this.y = y;
        this.solidArea = new Rectangle(12, 20, 24, 24);
        random= new Random();
        setDefaultValues();
        //getMonsterImage();
        
    }
    // public void getMonsterImage(){
    //     monsterUp=LoadResource.monsterUp;
    //     monsterDown=LoadResource.monsterDown;
    //     monsterLeft=LoadResource.monsterLeft;
    //     monsterRight=LoadResource.monsterRight;
    //     sprites = monsterUp;
    // }

    private void setDefaultValues() {
        speed=2;
        direction = "left";
        x = tileSize * 9;
        y = tileSize * 1;
         
       
    }
//    private void resetCollision() {
//        // Đặt lại trạng thái va chạm cho quái vật
//        canMoveUp = true;
//        canMoveDown = true;
//        canMoveLeft = true;
//        canMoveRight = true;
//    }

 public void update() {
  	
     
        gp.cChecker.checkTile(this); 
        gp.cChecker.checkBombForMoving(this); 
        gp.cChecker.checkPlayerForMonster(this, this);
        
        move();

        
        if (!canMoveUp && !canMoveDown && !canMoveLeft && !canMoveRight) {
            randomMove();
        }
 }

       

  
   

    public void randomMove(){
        int randomDirection=random.nextInt(4);
        switch(randomDirection){
            case 0:
            if (canMoveUp) {
                direction = "up";
                 
            }
            break;
        case 1: 
            if (canMoveDown) {
                direction = "down";
            }    
            break;
        case 2: 
            if (canMoveLeft) {
                direction = "left";
                
            }
            break;
        case 3: 
            if (canMoveRight) {
                direction = "right";
                
            }
            break;
        }
            
        
        
        

    }
   

    
    public void move() {
        boolean moved = false;

        switch (direction) {
            case "up":
                if (canMoveUp) {
                    y -= speed;
                    moved = true;
                }
                break;
            case "down":
                if (canMoveDown) {
                    y += speed;
                    moved = true;
                }
                break;
            case "left":
                if (canMoveLeft) {
                    x -= speed;
                    moved = true;
                }
                break;
            case "right":
                if (canMoveRight) {
                    x += speed;
                    moved = true;
                }
                break;
        }

        
        if (!moved) {
            randomMove();
        }

        resetCollision(true);  
    }
   

    
       // public void draw(Graphics2D g2) {
            
            // switch (direction) {
            //     case "up":
            //         sprites = monsterUp;
            //         break;
            //     case "down":
            //         sprites = monsterDown;
            //         break;
            //     case "left":
            //         sprites = monsterLeft;
            //         break;
            //     case "right":
            //         sprites = monsterRight;
            //         break;
            // }
            //g2.drawImage(, x, y, null);
            
            public void draw(Graphics2D g2) {
                
                    g2.setColor(Color.RED);
                    g2.fillRect(x, y, 32, 32);
                    
            }
            
        
        @Override
        public void beingHit() {
            super.beingHit();
            isDead = true;
        }
        

    }



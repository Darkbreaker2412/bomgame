package main;

import bomb.Bomb;
import bomb.Flame;
import entity.Entity;
import entity.Item;
import entity.Monster;

import java.awt.Rectangle;

import static MenuSetUp.DimensionSize.tileSize;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {

        this.gp = gp;
    }
   
    public void checkTile(Entity entity) {

        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entityLeftX + entity.solidArea.width - 2;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBotY = entityTopY + entity.solidArea.height - 2;

        int entityLeftCol = entityLeftX / tileSize;
        int entityRightCol = entityRightX / tileSize;
        int entityTopRow = entityTopY / tileSize;
        int entityBotRow = entityBotY / tileSize;

        int tile1, tile2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / tileSize;
                tile1 = gp.map.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.map.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
                    entity.canMoveUp = false;
                }
                break;
            case "down":
                entityBotRow = (entityBotY + entity.speed) / tileSize;
                tile1 = gp.map.mapTileNum[entityLeftCol][entityBotRow];
                tile2 = gp.map.mapTileNum[entityRightCol][entityBotRow];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
                    entity.canMoveDown = false;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / tileSize;
                tile1 = gp.map.mapTileNum[entityLeftCol][entityTopRow];
                tile2 = gp.map.mapTileNum[entityLeftCol][entityBotRow];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
                    entity.canMoveLeft = false;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / tileSize;
                tile1 = gp.map.mapTileNum[entityRightCol][entityTopRow];
                tile2 = gp.map.mapTileNum[entityRightCol][entityBotRow];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
                    entity.canMoveRight = false;
                }
                break;
        }
    }

    public boolean canPlaceBomb(Entity entity) {

        boolean canPlaceBomb = true;
        for (int i = 0; i < gp.bombs.size(); i++) {
            Bomb b = gp.bombs.get(i);
            if (entity.col() == b.col() && entity.row() == b.row()) {
                canPlaceBomb = false;
                break;
            }
        }
        return canPlaceBomb;
    }

    public void checkBombForMoving(Entity entity) {

        if (!entity.collisionOn) return;

        Rectangle solidArea = new Rectangle(entity.solidArea);
        solidArea.x += entity.x;
        solidArea.y += entity.y;
        switch (entity.direction) {
            case "up":
                solidArea.y -= entity.speed;
                gp.bombs.forEach((b) -> {
                    if (solidArea.intersects(b.solidArea) && !b.letPlayerPassThrough) {
                        entity.canMoveUp = false;
                    }
                });
                break;
            case "down":
                solidArea.y += entity.speed;
                gp.bombs.forEach((b) -> {
                    if (solidArea.intersects(b.solidArea) && !b.letPlayerPassThrough) {
                        entity.canMoveDown = false;
                    }
                });
                break;
            case "left":
                solidArea.x -= entity.speed;
                gp.bombs.forEach((b) -> {
                    if (solidArea.intersects(b.solidArea) && !b.letPlayerPassThrough) {
                        entity.canMoveLeft = false;
                    }
                });
                break;
            case "right":
                solidArea.x += entity.speed;
                gp.bombs.forEach((b) -> {
                    if (solidArea.intersects(b.solidArea) && !b.letPlayerPassThrough) {
                        entity.canMoveRight = false;
                    }
                });
                break;
        }
    }

    public static void checkEntityForFlame(Flame flame, Entity entity) {

        Rectangle solidArea = new Rectangle(entity.solidArea);
        solidArea.x += entity.x;
        solidArea.y += entity.y;
        if (flame.verticalSolidArea.intersects(solidArea)
                || flame.horizontalSolidArea.intersects(solidArea)) {
            entity.beingHit();
        }
    }

    public static void checkItemForFlame(Flame flame, Item item) {

        if (item.state == Item.States.shown && flame.duration > 1
                && (flame.verticalSolidArea.intersects(item.solidArea)
                || flame.horizontalSolidArea.intersects(item.solidArea))) {
            item.beingHit();
        }
    }

    public void checkPlayerForItem(Item item) {

        Rectangle solidArea = new Rectangle(gp.player.solidArea);
        solidArea.x += gp.player.x;
        solidArea.y += gp.player.y;
        if (item.state == Item.States.shown && solidArea.intersects(item.solidArea)) {
            item.beingPickedUp();
            gp.player.addScore(item.point);
            switch (item.name) {
                case "plus_1":
                    gp.player.addMoreFlame(1);
                    break;
                case "ugly_key":
                    break;
            }
        }
    }
    public void checkPlayerForMonster(Monster monster,Entity entity) {
            Rectangle monsterArea = new Rectangle(monster.solidArea);
            monsterArea.x += monster.x;
            monsterArea.y += monster.y;
            //monsterArea.x += gp.monster.x;
            //monsterArea.y += gp.monster.y;
    
            Rectangle solidArea = new Rectangle(gp.player.solidArea);
            solidArea.x += gp.player.x;
            solidArea.y += gp.player.y;
            if (monsterArea.intersects(solidArea)) {
    
                
                entity.beingHit();
                // monster.direction = monster.randomMove();
    
            }
        }
}
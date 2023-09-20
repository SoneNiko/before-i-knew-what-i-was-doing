/////////////////////////////////////////
// Author: Nikolas HEISE
// Date: 26.3.2021
/////////////////////////////////////////


// TODO
// Add vfx to item use()'s
// kill player slowly when outside of border
// damage vignette?
// model the items
// uncollected items get collected once the player resets the game add special case


//------------------------------------------
// Output related stuff REFACTOR THIS INTO OWN FILE

class MamerExtraCanvas2D {
    constructor(canvasStr) {
        //This is the kind of stuff done in S5 and S6
        this.canvas = document.getElementById(canvasStr);
        //console.log(this.canvas);
        this.ctx = this.canvas.getContext('2d');
        //console.log(this.ctx);
        this.maxWidth = this.canvas.width;
        this.maxHeight = this.canvas.height;
        //console.log("2D:", this.maxWidth, this.maxHeight);

        this.fontType1 = "bold italic 30px Arial";    
    }
    
    //PRIVATE, auxiliary
    clear() {
        this.ctx.fillStyle = "black";
        this.ctx.fillRect(0,0, this.maxWidth, this.maxHeight);
    }
    
    //PRIVATE
    // Gets a hue-saturation-lightness-alpha color
    // h is an integer ranging 0..360
    // s, l are integers ranging 0..100
    // alpha is a real number, range 0.0 to 1.0
    // Returns a CSS color with combination h, s, l, alpha
    getHSLA(h, s=100, l=50, alpha=1.0) {
        let ss = "";
        ss = ss + "hsla(" + h ;
        ss = ss + ", " + s + "%";
        ss = ss + ", " + l + "%";
        ss = ss + ", " + alpha + ")";    
        return ss;
    }

    //PRIVATE
    // Draws a grid LIMITED TO the size of the canvas
    drawGrid(linecolor = "black") {
        let index;

        this.ctx.lineWidth = 1;
        this.ctx.strokeStyle = linecolor; 
        this.ctx.fillStyle = linecolor;  
        this.ctx.font = "13px Arial";
        this.ctx.textAlign = "center";
        this.ctx.textBaseline = "middle";  
        //verticals
        for (index = 0; index <= this.maxWidth; index = index + 50) {
            this.ctx.beginPath();
            this.ctx.moveTo(index, 0);
            this.ctx.lineTo(index, 499);
            this.ctx.stroke();
            this.ctx.fillText(index, index, 0+15);  //text, x, y
            this.ctx.fillText(index, index, this.maxHeight-15);  //text, x, y
        }

        //horizontals
        for (index = 0; index <= this.maxHeight; index = index + 50) {
            this.ctx.beginPath();
            this.ctx.moveTo(0, index);
            this.ctx.lineTo(749, index);
            this.ctx.stroke();
            this.ctx.fillText(index, 0+25, index);  //text, x, y
            this.ctx.fillText(index, this.maxWidth-25, index);  //text, x, y
        }
        this.ctx.lineWidth = 1;  
    }
    
    //PRIVATE
    //By default text is painted left-aligned
    paintText(
        x, y, 
        message,
        fontColor="orange",
        fontType="bold 20px Arial",  
        textAlign="left", //left, center or right
    ) {
        this.ctx.lineWidth = 1;
        this.ctx.fillStyle = fontColor;
        this.ctx.strokeStyle = "black";
        this.ctx.font = fontType;
        this.ctx.textAlign = textAlign;
        this.ctx.textBaseline = "middle";
        
        this.ctx.fillText(message, x, y);
        this.ctx.strokeText(message, x, y);
        //console.log("MESSAGE:", message);
    }
    
    
    
    
    //PUBLIC (the only one)
    refresh() {
        this.clear();           
        this.drawGrid("red");
        this.paintText(
            100,20, 
            "Health: " + protagonist.health.toString() );
        this.paintText(
            100,120, 
            "Enemies:" + countEnemies(enemyList),
            "yellow"
        );
        this.paintText()
    }
    
    //PUBLIC (the only one)
    refresh2() {
        this.clear();           
        this.drawGrid("cyan");
        this.paintText(
            0,20, 
            "Health: " + protagonist.health.toString(),
            "lime");
        this.paintText(
            0,120, 
            "Enemies:" + countEnemies(enemyList),
            "lime"
        );
    }
    
}

class MamerExtraOverlay extends MamerExtraCanvas2D {
    //FINALLY there is not need at all for any kid of double-buffer
    //because this overlayed canvas is fully independent
    // mixing this 2D canvas and the p5.js
    // is managed by the browser without our intervention
    constructor(canvasStr) {
        super(canvasStr);
    }
    
    
    //PRIVATE (to be improved)
    paintGameEnding() {
        if (protagonist.health <= 0) {
            this.ctx.fillStyle = this.getHSLA(0,0,0, 0.4);
            this.ctx.fillRect(0,100,  750, 300); 
            this.paintText(
                375, 250,  
                "GAME OVER - YOU LOST",
                "red",
                "bold italic 50px Impact",
                "center"
            );
            this.paintText(
                375, 300,  
                "Press space to restart",
                "red",
                "bold italic 20px Impact",
                "center"
            );
        }
    }
    

    // I decide whats private i my code
    paintHealthProtagonist() {
        let healthbarstring = "";
        for(let i = 0; i < protagonist.health; i++) {
            healthbarstring += "#";
        }
        for (let p = protagonist.health; p < 20; p++) {
            healthbarstring += "-"
        }

        this.paintText(
            375, 470,
            "Health: [" + healthbarstring + "] (" + protagonist.health + ")", 
            protagonist.health < 10 ? (protagonist.health < 5 ? "red" : "orange") : "green",
            // If the Health is below 10 and 5 -> red otherwise orange or yellow, first timeI use ? : operator
            "bold 35px Arial",
            "center"
        );
    }
    

    paintNumberEnemies() {
        this.paintText(
            10, 80,
            "Enemies: " +countEnemies(enemyList), 
            "magenta",
            "bold 20px Arial",
            "left"
        );    
    }

    paintDifficulty() {
        this.paintText(
            10, 60,
            "Difficulty: " +Math.floor(difficulty/2), 
            "red",
            "bold 20px Arial",
            "left"
        ); 
    }

    paintCrossHair() {
        this.paintText(
            375, 250,
            "+", 
            "white",
            "50px Arial",
            "center"
        ); 
    }

    paintHeals() {
        this.paintText(
            10, 20,
            "Heals : " + protagonist.healStack.itemCount, 
            "lime",
            "bold 20px Arial",
            "left"
        ); 
    }

    paintPurges() {
        this.paintText(
            10, 40,
            "Purges : "+ protagonist.purgeStack.itemCount, 
            "orange",
            "20px Arial",
            "left"
        ); 
    }
    
    
    //ONLY PUBLIC method of this class...
    //Especially designed for the canvasOverlay
    //that is placed ON TOP of the 3D p5.js canvas
    refresh3() { 
        this.ctx.clearRect(0,0, this.maxWidth, this.maxHeight);
        
        this.paintGameEnding();

        this.paintCrossHair();
        this.paintHealthProtagonist(); // []
        this.paintNumberEnemies();
        this.paintDifficulty();

        this.paintHeals();
        this.paintPurges();
        
    }
}


//------------------------------------------
// Input related stuff REFACTOR THIS INTO OWN FILE AND PROTAGONIST CLASSES

let camDistance = 500;
let camX = camDistance;
let camY = 100;
let camZ = camDistance;
let camAngleHorizontal = 0;
let camAngleVertical = 30;
let camSensitivityHoriz = 0.15;
let camSensitivityVertic = 0.2;
let camSensitivityZoom = 0.2;
let dragSensitivity = 1.0;
let camTargetX = 0;
let camTargetY = 0;
let camTargetZ = 0;

let cameraMode = 4; // Starting in POV


function mouseDragged() {
    
    if (mouseButton === LEFT) {
        camAngleHorizontal += camSensitivityHoriz * movedX;
        if (camAngleHorizontal > 360) {
            camAngleHorizontal -=360;
        } else if (camAngleHorizontal < 0) {
            camAngleHorizontal +=360;
        }
    
        camAngleVertical += camSensitivityVertic * movedY;
        if (camAngleVertical > 90) {
            camAngleVertical = 90;
        } else if (camAngleVertical < -90) {
            camAngleVertical = -90;
        }
    } else if (mouseButton === RIGHT) {
        let horizImpactX, verticImpactX; 
        let horizImpactZ, verticImpactZ; 
        let mX, mY;
        mX = movedX;
        mY = movedY;
        
        // Horizontal movements of the mouse: movedX....
        horizImpactZ = -sin(camAngleHorizontal) * dragSensitivity;
        horizImpactX = cos(camAngleHorizontal) * dragSensitivity;
        camTargetX += horizImpactX * mX;
        camTargetZ += horizImpactZ * mX;
        //The camera accompanies the movement...
        camX += horizImpactX * mX;
        camZ += horizImpactZ * mX;
        
        // Vertical movements of the mouse: movedY....
        verticImpactZ = -cos(camAngleHorizontal) * dragSensitivity;
        verticImpactX = -sin(camAngleHorizontal) * dragSensitivity;
        camTargetX += verticImpactX * mY;
        camTargetZ += verticImpactZ * mY;
        //The camera accompanies the movement...
        camX += verticImpactX * mY;
        camZ += verticImpactZ * mY;
    }
}

function mouseWheel(event) {
    camDistance += camSensitivityZoom * event.delta;
    if (camDistance <= 0) {
        camDistance = 1; //impose at least a distance of 1
    }
    //console.log("mouseWheel() new camDistance:", camDistance);
}
function keyTyped() {
    if (key === "q") { //q
        protagonist.purgeStack.use();
    } //q
    
    if (key === "e") { //e
        protagonist.healStack.use();
    } //e
}

function inputHandler() {
    
    //console.log("User pressed:", keyCode);
    if ( keyIsDown("9".charCodeAt(0)) ) {
        // 9 --> zoom increase, decrease distance
        if ((camDistance-20)>0) { //only if result positive
            camDistance -= 20;
        }
    } else if ( keyIsDown("0".charCodeAt(0))  ) {
        // 0 --> zoom decrease, increase distance
        camDistance += 20;  
    }

    if ( keyIsDown(" ".charCodeAt(0)) && protagonist.health <= 0) {

        protagonist.health = 10;
        difficulty = 0;
        enemyList.forEach(element1 => {element1.alive = false;});
        bulletList.forEach(element1 => {element1.alive = false;});
        itemList.forEach(element1 => {element1.collected = true;});
        protagonist.healStack.itemCount = 2;
        protagonist.purgeStack.itemCount = 2;

    }
    
    // 22.2.2021 Monday (changing camera modes)
    if ( keyIsDown("1".charCodeAt(0)) ) {
        // 1 --> Camera mode 1 (orbit around 0,0,0)
        cameraMode = 1;
        camTargetX = 0;
        camTargetY = 0;
        camTargetZ = 0;
    } else if ( keyIsDown("2".charCodeAt(0)) ) {
        // orbiting around the protagonist

        cameraMode = 2;
    } else if ( keyIsDown("3".charCodeAt(0)) ) {
        //
        cameraMode = 3;
    } else if ( keyIsDown("4".charCodeAt(0)) ) {
        //
        cameraMode = 4;
        requestPointerLock();

    } 

    
    
    //26.2.2021 Shooting
    //if ( keyIsDown(65)  ) {
    //    protagonist.shoot();
    //}
    
    
    protagonist.theta += movedX * 0.4;
    //console.log(movedX);
    
    
    // removed the combined keystroke since its irrelevant with the new system
    
    if ( keyIsDown(UP_ARROW) || keyIsDown(87))  {
        protagonist.x += protagonist.speed * sin(protagonist.theta) * 2; // Combined the statement in
        protagonist.z += protagonist.speed * cos(protagonist.theta) * 2; // one line to save memory
    } 
    
    if ( keyIsDown(DOWN_ARROW) || keyIsDown(83)) { // Notice how the backwards movement is slower
        protagonist.x -= protagonist.speed * sin(protagonist.theta); // Combined the statement in
        protagonist.z -= protagonist.speed * cos(protagonist.theta); // one line to save memory        
    } 
    
    if ( keyIsDown(LEFT_ARROW) || keyIsDown(65)){
        protagonist.x += sin(protagonist.theta-90) * protagonist.speed
        protagonist.z += cos(protagonist.theta-90) * protagonist.speed
        //protagonist.theta = (protagonist.theta - 5) % 360;
    }
    
    if ( keyIsDown(RIGHT_ARROW) || keyIsDown(68)) {
        protagonist.x += sin(protagonist.theta+90) * protagonist.speed
        protagonist.z += cos(protagonist.theta+90) * protagonist.speed
        //protagonist.theta = (protagonist.theta + 5) % 360;
    }
        
}

function mouseClicked() {
    if (cameraMode == 4) {
        requestPointerLock();
    }
    protagonist.shoot();

}


function cameraControl() {
    // TEACHER's ORBIT CONTROL
    //THE FOLLOWING IS TO GET RID OF orbitControl()
    //distance2: projection on horizontal plane XZ
    let distance2 = camDistance * cos(camAngleVertical);
    
    if (cameraMode === 1) {
        //Camera position   
        camX = camTargetX + distance2 * sin(camAngleHorizontal);
        camZ = camTargetZ + distance2 * cos(camAngleHorizontal);
        camY = camTargetY + camDistance * sin(camAngleVertical); 
        // - The last 0, -1, 0 is to set Y positive upwards...
        // - camTargetXYZ will be normally (0,0,0)
        camera(
            camX, camY, camZ,
            camTargetX, camTargetY, camTargetZ,
            0, -1, 0
        );
    } else if (cameraMode === 2) {
        camTargetX = protagonist.x;
        camTargetY = protagonist.y;
        camTargetZ = protagonist.z;
        camX = camTargetX + distance2 * sin(camAngleHorizontal);
        camZ = camTargetZ + distance2 * cos(camAngleHorizontal);
        camY = camTargetY + camDistance * sin(camAngleVertical); 
        // - The last 0, -1, 0 is to set Y positive upwards...
        // - camTargetXYZ will be normally (0,0,0)
        camera(
            camX, camY, camZ,
            camTargetX, camTargetY, camTargetZ,
            0, -1, 0
        );        
    } else if (cameraMode === 3) { 
        // 1st person view from behind
        // UNITARY VECTOR
        let dirX = sin(protagonist.theta);
        let dirZ = cos(protagonist.theta);
        let dirY = 0; //horizontal plane
        // TARGET AHEAD
        camTargetX = protagonist.x + dirX * 300;
        camTargetY = protagonist.y + dirY * 300;
        camTargetZ = protagonist.z + dirZ * 300;
        // CAMERA BEHIND (and higher)
        camX = protagonist.x - dirX * 300;
        camZ = protagonist.z - dirZ * 300;
        camY = protagonist.y + 200; 
        camera(
            camX, camY, camZ,
            camTargetX, camTargetY, camTargetZ,
            0, -1, 0
        );         
        
    } else if (cameraMode === 4) { 
        // INSIDE THE PROTAGONIST
        // UNITARY VECTOR
        let dirX = sin(protagonist.theta);
        let dirZ = cos(protagonist.theta);
        let dirY = 0; //horizontal plane
        // TARGET AHEAD
        camTargetX = protagonist.x + dirX * 300;
        camTargetY = protagonist.y + dirY * 300;
        camTargetZ = protagonist.z + dirZ * 300;
        // CAMERA BEHIND (and higher)
        camX = protagonist.x;
        camZ = protagonist.z;
        camY = protagonist.y; 
        camera(
            camX, camY + 50, camZ,
            camTargetX, camTargetY, camTargetZ,
            0, -1, 0
        );         
        
    }
    
    
    
    
    // END OF TEACHER's ORBIT CONTROL    
}


//------------------------------------------
// Base class
class MamerObject {
    constructor(posX, posY, posZ, diameter=20) {
        this.objectType = "object"; //generic
        
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.speedX = 0;
        this.speedY = 0;
        this.speedZ = 0;
        
        //We should support both RGB and HSL
        // currently only RGB
        this.colorR = 255;
        this.colorG = 255;
        this.colorB = 255;   
        // we can get an "easy" parameter for the user,
        // but store it in the most convenient way for programming
        this.radius = Math.round(diameter / 2);
    }
    
    setSpeeds(speedX, speedY, speedZ) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
    }
    
    setColors(r, g, b) {
        this.colorR = r;
        this.colorG = g;
        this.colorB = b;
    }
    
    // Here we'll have some conditional
    // to enable other varieties of movements, such as
    // re-appear, random, etc...
    move() {
        this.moveBounce();
    }
    
    moveBounce() {
        this.x = this.x + this.speedX;
        if ((this.x + this.radius  > 500) && (this.speedX > 0)) {
            this.speedX = (-1) * abs(this.speedX);
        } else if ((this.x - this.radius < -500) && (this.speedX < 0)) {
            this.speedX = abs(this.speedX);                   
        }

        this.y = this.y + this.speedY;
        if ((this.y + this.radius  > 300) && (this.speedY > 0)) {
            this.speedY = (-1) * abs(this.speedY);
        } else if ((this.y - this.radius < 0) && (this.speedY < 0)) {
            this.speedY = abs(this.speedY);                   
        }

        this.z = this.z + this.speedZ;
        if ((this.z + this.radius  > 500) && (this.speedZ > 0)) {
            this.speedZ = (-1) * abs(this.speedZ);
        } else if ((this.z - this.radius < -500) && (this.speedZ < 0)) {
            this.speedZ = abs(this.speedZ);                   
        }
    }
    
    paint(){
        //We paint the object itself, by default a sphere...
        push(); 
        //stroke(this.colorR, this.colorG, this.colorB);
        fill(this.colorR, this.colorG, this.colorB);
        translate(this.x, this.y, this.z);
        sphere(this.radius, 8, 8);
        pop(); 
    }//paint()
      
}

//------------------------------------------
// Protagonist related classes

class MamerProtagonist {
    constructor(posX, posY, posZ, diameter=50) {
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.diameter = diameter;
        
        this.theta = 0; //orientations XZ plane
        
        //linear speed when arrow keys pressed
        this.speed = 10;
        
        // 15.3.2021 728 Health Points of protagonist
        this.health = 20;

        this.healStack = new MamerHeal(2, 0, 0, 0, true);
        this.purgeStack = new MamerPurge(2, 0, 0, 0, true);
    }
    
    paint() {
        
        let dirX = sin(this.theta); // -1....1
        let dirZ = cos(this.theta); // -1....1
        let dirY = 0;
        
        //19.3.2021 depict homer differently
        // according to health
        let r, g, b;
        if (this.health <= 0) {
            r = 0;  g=0; b=0;
        } else if (this.health <= 5) {
            r=150; g=150; b=150;
        } else {
            r = 255;
            g = 255;
            b = 0;
        }
        
        let c = color(r, g, b);
        
        myHomerOriented(
            c, this.diameter,
            this.x, this.y, this.z,
            dirX, dirY, dirZ
            );
        
        
        //OPTIOn: put here the collisions with bullets
        // 19.3.2021  (enemyList)
        let dX, dY, dZ; // distance protagonist vs each enemy
        let d;  //Euclidean distance
        let enemy;
        for (let i=0; i < enemyList.length; i++) {
            enemy = enemyList[i];
            dX = enemy.x - this.x;
            dY = enemy.y - this.y;
            dZ = enemy.z - this.z;
            d = Math.sqrt(dX*dX + dY*dY + dZ*dZ);
            if (d < (this.diameter/2)+(enemy.radius) ){
                //collision
                this.health = this.health - 1;
                enemy.alive = false;
            } 
        }

    }
    shoot() {
        // Letter A
        //console.log("Hommer shooting");
        
        let bb;
        
        bb = new MamerProtagonistBullet(protagonist.x, protagonist.y, protagonist.z);
        bb.setColors(255,255,0); // yellow
        // quick exercise
        let dirX = sin(protagonist.theta);
        let dirZ = cos(protagonist.theta);
        let sX = dirX * 45;
        let sY = 0;
        let sZ = dirZ * 45;
        bb.setSpeeds(sX, sY, sZ);
        bulletList.push(bb);
    }
}

class MamerProtagonistBullet extends MamerObject {
    constructor(posX, posY, posZ, diameter=20) {
        super(posX, posY, posZ, diameter);
        this.objectType = "bullet1";
        
        // 5.3.2021 Activity 726 bullets self-destroy
        this.alive = true; //boolean
    }
    
    paint() {
        push();
        noStroke();
        if (this.alive === true) {
            emissiveMaterial(this.colorR, this.colorG, this.colorB);    
        } else {
            emissiveMaterial(150, 150, 100); //brownish   
        }
     
        translate(this.x, this.y, this.z);
        sphere(this.radius, 8, 8);
        pop();   
    }
    
    move() {
        this.x = this.x + this.speedX;
        this.y = this.y + this.speedY;
        this.z = this.z + this.speedZ;
        // 5.2.2021 Detect OFF boundaries
        // DISTANCE this bullet and "protagonist" (homer)
        let dX = this.x - protagonist.x;
        let dY = this.y - protagonist.y;
        let dZ = this.z - protagonist.z;
        let distance = Math.sqrt(dX*dX + dY*dY + dZ*dZ);
        if (distance > 800) {
            this.alive = false;
        }  
        
        //8.3.2021 - 727 DETECT COLLISIONS with enemyList
        let enemy;
        for (let i=0; i < enemyList.length; i++) {
            enemy = enemyList[i];
            let dX = this.x - enemy.x;
            let dY = this.y - enemy.y;
            let dZ = this.z - enemy.z;
            let distance = Math.sqrt(dX*dX + dY*dY + dZ*dZ);
            if (distance <= (this.radius + enemy.radius) ) {
                this.alive = false;
                enemy.alive = false;
            }
            
        }
        
    }  
}

//------------------------------------------
class MamerColectibleItemStack extends MamerObject{
    constructor(amount, x, y, z, collected = false) {
        super(x, y, z, 10)
        this.itemCount = amount;
        this.collected = collected;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    use() {
        if (this.itemCount === 0) return false;
        this.itemCount--;
        return true; // return true if item can be used
    }

    move() {
        let dX = protagonist.x - this.x;
        let dY = protagonist.y - this.y;
        let dZ = protagonist.z - this.z;

        let distance = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2) + Math.pow(dZ, 2));
        if (distance < 40) { //Cant be bothered to take the radius 
            this.collected = true;
        } 

    }

    
}

class MamerHeal extends MamerColectibleItemStack {
    constructor(amount = 1, x = 0, y = 0, z = 0, collected = false) {
        super(amount, x, y, z, collected)
    }

    paint() {
        if (this.collected) return;
        myHealItem(this.x, this.y, this.z);
    }

    move() { 
        super.move(); // Same is in parent class
    }

    use() {
        
        if (!super.use()) return;
        protagonist.health = 20; // TODO: Set starting health of protagonist to 20
        //TODO: torus shaped shockwave in green
    }

}

class MamerPurge extends MamerColectibleItemStack {
    constructor(amount = 1, x = 0, y = 0, z = 0, collected = false) {
        super(amount, x, y, z, collected)
    }

    move() { 
        super.move(); // Same is in parent class
    }

    paint() {
        if (this.collected) return;
        myPurgeItem(this.x, this.y, this.z);
    }

    use() {
        if (!super.use()) return;
        enemyList.forEach((element) => {
            element.alive = false;
        });
        //TODO: torus shaped shockwave in red
    }
}

//------------------------------------------
// Enemy Related classes

class MamerEnemyMafia extends MamerObject {
    constructor(posX, posY, posZ, diameter=20) {
        super(posX, posY, posZ, diameter);
        this.objectType = "enemyMafia";
        
        // For destroying
        this.alive = true; //boolean
    }
    
    paint() {
        let c = color(this.colorR, this.colorG, this.colorB);
        if ((this.speedX ===0)
            &&(this.speedY === 0)
            &&(this.speedZ === 0)) {
            //no speed --> they will look to Z positive
            //alternatively we could look into
            //some "latest movement" stored somewhere
            myZombiOriented(
                c, this.radius * 2,
                this.x, this.y, this.z,
                0,0,1
                );                        
        } else {
            //normal case
            myZombiOriented(
                c, this.radius * 2,
                this.x, this.y, this.z,
                this.speedX, this.speedY, this.speedZ
                );            
        }
    } 

    move() {
        this.x += this.speedX;
        this.y += this.speedY;
        this.z += this.speedZ;


        let vX = protagonist.x - this.x;  //vector homer-->enemy
        let vY = protagonist.y - this.y;
        let vZ = protagonist.z - this.z;
        //find normal vector
        let d = Math.sqrt(vX*vX + vY*vY + vZ*vZ);
        this.setSpeeds((vX / d)*2, (vY / d)*2, (vZ / d)*2);
    }
    
}


class MamerEnemyDemon extends MamerObject {
    constructor(posX, posY, posZ, diameter=20) {
        super(posX, posY, posZ, diameter);
        this.objectType = "enemyDemon";
        
        // For destroying
        this.alive = true; //boolean
    }
    
    paint() {
        let c = color(this.colorR, this.colorG, this.colorB);
        if ((this.speedX === 0)
            &&(this.speedY === 0)
            &&(this.speedZ === 0)) {
            //no speed --> they will look to Z positive
            this.speedZ = 1; // Changed the complicated way ofchecking for zero speed
        }
        myHomerHellOriented(
            c, "black", "white", "yellow", this.radius * 2,
            this.x, this.y, this.z,
            this.speedX, this.speedY, this.speedZ
            );  
    } 
    
    // The movements are "bounce" by default,
    // As defined in the parent "MamerObject" class...
    move() {
        super.move();
        // in addition, this enemy HORNED will also shoot
        // 15.3.2021
        //Lottery on whether we shoot or not
        let number = randBetween(1, 1000);
        if (number < 10) {
            // Bb is the NEW BULLET
            let bb = new MamerEnemyBullet(
                this.x,
                this.y,
                this.z,
                10  //small bullets
            );
            // define the speed now
            let vX = protagonist.x - bb.x;
            let vY = protagonist.y - bb.y;
            let vZ = protagonist.z - bb.z;
            let d = Math.sqrt(vX*vX + vY*vY + vZ*vZ);
            //normal vector (length 1)
            let nX = vX / d;
            let nY = vY / d;
            let nZ = vZ / d; 
            //now the speeds
            bb.setSpeeds(nX*15, nY*15, nZ*15);
            bb.setColors(
                this.colorR,
                this.colorG,
                this.colorB
            );
            enemyList.push(bb);
        }  
    }
    
    
}


class MamerEnemyBullet extends MamerObject {
    constructor(posX, posY, posZ, diameter=20) {
        super(posX, posY, posZ, diameter);
        this.objectType = "enemyBullet";
        
        // For destroying
        this.alive = true; //boolean
    }
    
    paint() {
        let c = color(this.colorR, this.colorG, this.colorB);
        paintRocket2oriented(
            c, this.radius*2,
            this.x, this.y, this.z,
            this.speedX, this.speedY, this.speedZ
        );   
    }
    
    move() {
        this.x = this.x + this.speedX;
        this.y = this.y + this.speedY;
        this.z = this.z + this.speedZ;
        // 5.2.2021 Detect OFF boundaries
        // DISTANCE this bullet and "protagonist" (homer)
        let dX = this.x - protagonist.x;
        let dY = this.y - protagonist.y;
        let dZ = this.z - protagonist.z;
        let distance = Math.sqrt(dX*dX + dY*dY + dZ*dZ);
        if (distance > 2000) {
            this.alive = false;
        }      
    }
}

//------------------------------------------
// Functions called by draw (e.g. functions that need to be executed every frame)

function movements() { // alias update
    
    // Updating bullets
    for(let i=0; i < bulletList.length ; i++) { bulletList[i].move(); }   
    // Removing dead bullets
    for(let i=bulletList.length-1; i>=0; i--) {
        if (bulletList[i].alive === false) {
            bulletList.splice(i, 1);
        }
    }

    // Updating Enemies
    for(let i=0; i < enemyList.length ; i++) { enemyList[i].move(); }
    // Delete "dead" enemies
    for(let i=enemyList.length-1; i>=0; i--) {
        if (enemyList[i].alive === false) {
            enemyList.splice(i, 1);
        }
    }

    // Updating Items
    for(let i=0; i < itemList.length ; i++) { itemList[i].move(); }
    // Delete "dead" enemies
    for(let i=itemList.length-1; i>=0; i--) {
        if (itemList[i].collected === true && protagonist.health <= 0) { // If dead
            itemList.splice(i, 1);
        } else if (itemList[i].collected === true && protagonist.health > 0) { // If alive
            // Collect the item
            if (itemList[i].constructor.name === "MamerHeal") {
                protagonist.healStack.itemCount++;
            } else if (itemList[i].constructor.name === "MamerPurge") {
                protagonist.purgeStack.itemCount++;
            }
            
            itemList.splice(i, 1);
            
        }
    }


    // Spawning items

    if (randBetween(1, 1000) <= 5) { // Low chance
        if (itemList.length > 5) return;

        x = randBetween(-500, 500);;
        z = randBetween(-500, 500);;

        while(Math.sqrt(Math.pow(protagonist.x-x, 2) + Math.pow(protagonist.z*z, 2)) < 100) {
            x = randBetween(-500, 500);
            z = randBetween(-500, 500);
        }

        let item;

        if (randBetween(0, 1) < 1) {
            item = new MamerHeal(1, x, 50, z, false);
        } else {
            item = new MamerPurge(1, x, 50, z, false);
        }

        itemList.push(item);
        
    }
    
    // Spawning new enemies
    if (randBetween(1, 1000) <= difficulty) { // Zombie Spawn Chances: increasing every 1000ms
        if (countEnemies(enemyList) > 20) return; // Dont spawn to many enemies
        
        x = randBetween(-500, 500);;
        z = randBetween(-500, 500);;

        while(Math.sqrt(Math.pow(protagonist.x-x, 2) + Math.pow(protagonist.z*z, 2)) < 100) {
            x = randBetween(-500, 500);
            z = randBetween(-500, 500);
        }

        //new zombi enemy
        let bb;

        if (randBetween(0, 1) < 1) { // Spawn a demon
            bb = new MamerEnemyDemon(
                x, // Random x
                50, // fixed "height"
                z, // Random z
                40 // fixed "size"
            );
            bb.setColors(255,0,0);
            bb.setSpeeds((Math.random() * 10) - 5, 0, (Math.random() * 15) - 7.5) // Demons are faster but do not move towards the player
        } else {
            bb = new MamerEnemyMafia( // Spawn a mafioso
                x, // Random x
                50, // fixed "height"
                z, // Random y
                40 // fixed "size"
            );
            bb.setColors(100,100,50);
            // Special orientation towards protagonist
            let vX = protagonist.x - bb.x;  //vector homer-->enemy
            let vY = protagonist.y - bb.y;
            let vZ = protagonist.z - bb.z;
            //find normal vector
            let d = Math.sqrt(vX*vX + vY*vY + vZ*vZ);
            let nX = vX / d;
            let nY = vY / d;
            let nZ = vZ / d;
            bb.setSpeeds(nX*5, nY*5, nZ*5);
        }
        
        enemyList.push( bb );
    } 

}


function scene() {// alias paint
    background( color(50) ); 
    colorMode(RGB);

    if (lightCheckbox.checked()) {
        ambientLight(40);
        directionalLight(255, 255, 255, -1, -1, 0);
    }
    lightFalloff(0.5, 0.002, 0);
    
    
   
    //Objects
    
    protagonist.paint();
    
    // 26.2.2021 - Activity 725
    for (let i=0; i < bulletList.length; i++) {
        bulletList[i].paint();
    }

    for (let i=0; i < itemList.length; i++) {
        itemList[i].paint();
    }
    
    // 727 Enemies1
    for (let i=0; i < enemyList.length; i++) {
        enemyList[i].paint();
    }
    
    
    // 711 starting - Simple huge "terrain"
    push();
    fill("lightgray");
    translate(0, -3, 0);
    scale(1100, 4, 1100);
    box(1, 1, 1); //box of size 1...
    pop();

    // Walls that color red if the player is to close
    push();
    fill(color(255, 0, 0, -255 + protagonist.z));
    translate(0,50,500);
    scale(1000, 150, 1);
    box(1,1,1);
    pop();

    push();
    fill(color(255, 0, 0, -255 - protagonist.z));
    translate(0,50,-500);
    scale(1000, 150, 1);
    box(1,1,1);
    pop();

    push();
    fill(color(255, 0, 0, -255 + protagonist.x));
    translate(500,50,0);
    scale(1, 150, 1000);
    box(1,1,1);
    pop();

    push();
    fill(color(255, 0, 0, -255 - protagonist.x));
    translate(-500,50,0);
    scale(1, 150, 1000);
    box(1,1,1);
    pop();
    
    
} // formerly known as myScene


//------------------------------------------
// Global vars

// Game Objects
let protagonist;  // MAIN protagonist
let bulletList = [];
let enemyList = [];
let itemList = [];

// UI objects
let lightCheckbox;
var P5JSCanvas;
var panelOverlay;

// Game logic vars
let difficulty = 0;
var focused = true;
let fCount = 0;

//------------------------------------------
// P5JS functions
function setup(){
    frameRate(30);
    
    
    lightCheckbox = createCheckbox('light', false)
    
    //console.log("Inside setup..");
    P5JSCanvas = createCanvas(750, 500, WEBGL);
    P5JSCanvas.parent("p5canvasDiv");
    
    P5JSCanvas.style.position = "absolute";
    P5JSCanvas.style.left = "0";
    P5JSCanvas.style.top = "0";
    
    //panelOverlay = document.createElement('canvas');
    //panelOverlay.parent
    panelOverlay = new MamerExtraOverlay("canvasOverlay");
    
    
    // removed the bottom and sidepanel to save screen space

    angleMode( DEGREES );
    
    // disable right-click (context menu) in the browser
    document.oncontextmenu = function() {
        return false;
    }
    
    
    protagonist = new MamerProtagonist(0,40,0, 40);
    protagonist.theta = 0;

    requestPointerLock();
    

}//setup


function draw(){

    //Subsystem to spare computing cycles (P.FURCHE)
    if (!focused) { return; }

    // Counting frames per second
    fCount++;
    
    // Using this because neither
    // keyPressed() or keyTyped() worked properly
    // with repeating signals 
    if (keyIsPressed || movedX != 0) { inputHandler(); }
    
    cameraControl();
    
    movements();
    
    scene();

    debugAxes();

    
}//draw

setInterval(() => {
    if (!focused) { return; }
    difficulty++;

    document.getElementById('frameCounter').innerText = "FPS: " + fCount;
    fCount = 0; // At the end of a second fCount is reset and frames per second are displayed

}, 1000)

setInterval(() => {
    if (!focused) { return; }

    panelOverlay.refresh3(); // Moved this here to save time

}, 100)


// Special code to limit wasting computing cycles
// inside the school (contribution of P.FURCHE)
window.onblur = function (){
    focused = false;
    frameRate(0);
}
window.onfocus = function (){
    focused = true;
    frameRate(30);
}
//change tab same as window (?)
document.onblur = window.onblur;
document.focus = window.focus;

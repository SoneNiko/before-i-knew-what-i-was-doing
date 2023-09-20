
//---------------------------------------------------
// A simple pinetree (cones)
function myPinetree1(
    leavesColor = "green",
    trunkColor = "brown"
    ) {

    stroke("black"); //color of the wires
    strokeWeight(1); // probably better to put this outside...

    // The trunk, in brown
    push();
    fill(trunkColor);
    translate(0, 50, 0); //higher 50 units
    // radius, height, detailX, detailY, bottomCap, topCap
    cylinder(25, 100, 9, 1, true, false);
    pop();  
    
    // A first green cone, for the leaves
    push();
    fill(leavesColor);
    translate(0, 180, 0);  //nicer
    cone(100, 200, 9, 1, true);    
    pop();
    
    // A second green cone, for the leaves
    push();
    fill(leavesColor);
    translate(0, 250, 0); 
    cone(90, 200, 9, 1, true);    
    pop();
}//myPinetree1


//--------------------------------------------------------
// 25.10.2020
// Originally in 709, modified for 711...
// The SIMPSON we did in 709, but now modified
// so that the basic model "upwards" is Y-positive
// Rotations made using DEGREES
function mySimpson(color = "yellow") {
    
    //head
    push();
    fill(color);
    noStroke();
    scale(0.5, 1, 0.5);
    sphere(100, 8, 8);
    pop();
    
    //eye-left
    push();
    fill("white");
    stroke("lightgray")
    translate(20, 40, 25);
    scale(1, 1, 1);
    sphere(20, 8, 8);
    pop();
    //pupil of the eye left
    push();
    fill("black");
    noStroke();
    translate(20, 40, 45);
    scale(1, 1, 1);
    sphere(5, 8, 8);
    pop();
        
    //eye-right
    push();
    fill("white");
    stroke("lightgray")
    translate(-20, 40, 25);
    scale(1, 1, 1);
    sphere(20, 8, 8);
    pop();
    //pupil of the eye right
    push();
    fill("black");
    noStroke();
    translate(-20, 40, 45);
    scale(1, 1, 1);
    sphere(5, 8, 8);
    pop();
    
    //mouth
    push();
    fill("red");
    //stroke("black");
    noStroke();
    translate(0, 0, 7);
    rotateX( -80 );
    scale(0.7, 0.9, 0.9);
    torus(50, 7, 8, 8); //detail 8
    pop();
        
}//mySimpson


//---------------------------------------
// 30.11.2020 - 717 mySimpsonOrientation
// Adding scalability + orientation
function myHomerOriented(
    color = "yellow",
    size = 100,  //diameter horizontal
    posX = 0,
    posY = 0,
    posZ = 0,
    directionX = 0,
    directionY = 0,
    directionZ = 1)
{
    let s = size / 100; //scaling factor
    let vHomerDirection = createVector(
        directionX, 
        directionY, 
        directionZ);
    vHomerDirection.normalize();
    let theta = atan2(vHomerDirection.x, vHomerDirection.z);
    let phi = asin(vHomerDirection.y);

    push();
    translate(posX, posY, posZ);
    rotateY(theta);
    rotateX(-phi);
    scale(s, s, s);
    mySimpson(color);
    pop();
}



//718
//wrapper function doing 2 things:
// + Modify the "native paintRocket1"
//   so that it paints the rocket facing Z positive
// + Do the same transformations as with homer oriented
function paintRocket2oriented(
    couleur, size, 
    posX=0, posY=0, posZ=0,
    directionX = 0,
    directionY = 1,
    directionZ = 0)
{
    let vRocketDirection = createVector(
        directionX, 
        directionY, 
        directionZ);
    vRocketDirection.normalize();
    let theta = atan2(vRocketDirection.x, vRocketDirection.z);
    let phi = asin(vRocketDirection.y);
    
    
    //call original rocket, but with a rotation...
    push();
    translate(posX, posY, posZ);
    rotateY(theta);
    rotateX(-phi);    
    paintRocket2(couleur, size)
    pop(); 
}

// 718, adaptign the "native" function so that the pointy head
// faces Z positive...
function paintRocket2(couleur, size) {
    push();
    rotateX(90);
    paintRocket1(couleur, size);
    pop();
}


//pre718 - Example of "rocket", initially oriented
//upwards, i.e. with pointy end facing Y positive...
function paintRocket1(couleur, size) {
    s = size / 50; //diameter of the body was 50
    
    //head of the rocket, a pyramid
    push();
    fill(couleur);
    stroke("black"); //noStroke();    
    //cylinder100--> half is 50...
    //head cone is 60, half is 30...
    translate(0, (50+30)*s, 0);
    scale(s, s, s);
    cone(25, 60, 8, 1); //radius, height, detailX, detailY
    pop();
    
    //body of the rocket, a cylinder
    push();
    fill(couleur);
    stroke("black"); //noStroke() 
    scale(s, s, s);
    cylinder(25, 100, 8, 1); //radius, height, detailX, detailY
    pop();  
}


////////////////////////////////////////////////////

function paintHelicopter2oriented(
    angleRotationHelix = 0,
    theColor = "red",
    size = 100,
    posX = 0, posY = 0, posZ = 0,
    directionX = 0, directionY = 0, directionZ = 1
)
{
    let s = size / 100; //scaling factor    
    let vHelicopterDirection = createVector(
        directionX, 
        directionY, 
        directionZ);
    vHelicopterDirection.normalize();
    let theta = atan2(vHelicopterDirection.x, vHelicopterDirection.z);
    let phi = asin(vHelicopterDirection.y);
    
    //call original helicopter, but with a rotation...
    push();
    translate(posX, posY, posZ);
    rotateY(theta);
    rotateX(-phi); 
    scale(s, s, s);
    paintHelicopter1(angleRotationHelix, theColor );
    pop();
    
}


//location at 0, 0, 0
function paintHelicopter1(
    angleRotationHelix = 0,
    theColor = "red") 
{
    //Helix, main rod
    push();
    fill("black");
    noStroke();
    translate(0, 65, 0);
    cylinder(10, 21, 8, 1); //radius, height, detailX, detailY
    pop();     
    
    push();
    rotateY(angleRotationHelix);
    //Helix, 3 arms
    //1st
    push();
    fill("gray");
    translate(0, 70, -45); //on top of tail
    scale(10, 5, 100);
    box(1);
    pop();
    //2nd
    push();
    fill("lightgray");
    rotateY(120);
    translate(0, 70, -45); //on top of tail
    scale(10, 5, 100);
    box(1);
    pop();    
    //3rd
    push();
    fill("white");
    rotateY(240);
    translate(0, 70, -45); //on top of tail
    scale(10, 5, 100);
    box(1);
    pop();  
    /////
    pop(); //for the angleRotationHelix
    
    //body of the helicopter
    push();
    let glassColor = color(0,255,255, 100); //0..255 alpha
    fill(glassColor); //fill("cyan");
    noStroke();
    rotateY(90);
    scale(15, 10, 10); //150, 100, 100
    sphere(5);
    pop();
    
    //tail of the helicopter: a cone
    push();
    //fill(color(255,0,0, 255)); //opaque
    fill(theColor);
    stroke("black");
    translate(0,30,-70);
    rotateX(-60);
    cone(45, 200, 8, 1);
    pop();
    

    //right leg/bar
    push();
    fill("gray");
    noStroke();
    translate(40, -40, 0);
    rotateX(90);
    cylinder(6, 150, 8, 1); //radius, height, detailX, detailY
    pop();  
    
    //right leg/bar
    push();
    fill("gray");
    noStroke();
    translate(-40, -40, 0);
    rotateX(90);
    cylinder(6, 150, 8, 1); //radius, height, detailX, detailY
    pop();  
    
}


//--------------------------------------------------------
// BY THE TEACHER for activity 727 Enemies1
function myZombi(color = "turquoise") {
    //head
    push();
    fill(color);
    noStroke();
    scale(0.5, 1, 0.5);
    sphere(100, 8, 8);
    pop();
    
    //eye-left
    push();
    fill("white");
    stroke("lightgray")
    translate(20, 40, 25);
    scale(1, 1, 1);
    sphere(20, 8, 8);
    pop();
    // No black pupils
        
    //eye-right
    push();
    fill("white");
    stroke("lightgray")
    translate(-20, 40, 25);
    scale(1, 1, 1);
    sphere(20, 8, 8);
    pop();
    //No black pupils
    
    //Hat base
    push();
    fill("black");
    noStroke();
    translate(0, 75, 7);
    scale(1, 0.1, 1); //flattened
    sphere(80, 8, 8); //detail 8
    pop();
    //Hat top ( a cylinder)
    //head radius 100; hat base:75
    push();
    fill("black");
    noStroke();
    translate(0, 30+75, 7);
    scale(1, 1, 1); 
    cylinder(40,60, 8, 8); //detail 8
    pop();
        
}//myZombi


//---------------------------------------
// By the teacher for 727
function myZombiOriented(
    color = "turquoise",
    size = 100,  //diameter horizontal
    posX = 0,
    posY = 0,
    posZ = 0,
    directionX = 0,
    directionY = 0,
    directionZ = 1)
{
    let s = size / 100; //scaling factor
    let vZombiDirection = createVector(
        directionX, 
        directionY, 
        directionZ);
    vZombiDirection.normalize();
    let theta = atan2(vZombiDirection.x, vZombiDirection.z);
    let phi = asin(vZombiDirection.y);

    push();
    translate(posX, posY, posZ);
    rotateY(theta);
    rotateX(-phi);
    scale(s, s, s);
    myZombi(color);
    pop();
}

//--------------------------------------------------------
// BY THE TEACHER for activity 728 Enemies2
function myHomerHell(
    colorSkin = "orange",
    colorEyes = "black",
    colorPupils = "white",
    colorHorns = "red"
) {
    //head
    push();
    fill(colorSkin);
    noStroke();
    scale(0.5, 1, 0.5);
    sphere(100, 8, 8);
    pop();
    
    //eye-left
    push();
    fill(colorEyes);
    noStroke(); //stroke("lightgray")
    translate(20, 40, 30);
    rotateZ(+30); //angry
    scale(1, 0.7, 1); //not circular eyes
    sphere(20, 8, 8);
    pop();
    //pupil of the eye left
    push();
    fill(colorPupils);
    noStroke();
    translate(20, 40, 48);
    scale(1, 1, 1);
    sphere(5, 8, 8);
    pop();
           
    //eye-right
    push();
    fill(colorEyes);
    noStroke(); //stroke("lightgray")
    translate(-20, 40, 30);
    rotateZ(-30); //angry
    scale(1, 0.7, 1); //not circular eyes
    sphere(20, 8, 8);
    pop();
    //pupil of the eye right
    push();
    fill(colorPupils);
    noStroke();
    translate(-20, 40, 48);
    scale(1, 1, 1);
    sphere(5, 8, 8);
    pop(); 
    
    //mouth - just a disk
    push();
    fill(colorHorns);
    noStroke();        
    translate(0, -10, 15); //Z shifted forward
    scale(1, 0.2, 1);
    sphere(40, 8, 8);
    pop();
        
    //Top horn
    push();
    fill(colorHorns);
    stroke("black");        
    translate(0, 120, 0);
    scale(1, 1, 1);
    cone(15, 90, 8, 8);        
    pop();
    //Left horn (X positive)
    push();
    fill(colorHorns);
    stroke("black");     
    translate(30, 100, 20);
    rotateX(30); 
    rotateZ(-25);    
    scale(1, 1, 1);
    cone(15, 90, 8, 8);        
    pop();
    //Right horn (X positive)
    push();
    fill(colorHorns);
    stroke("black");     
    translate(-30, 100, 20);
    rotateX(30); 
    rotateZ(+25);    
    scale(1, 1, 1);
    cone(15, 90, 8, 8);        
    pop();
        
}//myHomerHell


//---------------------------------------
// By the teacher for 728
function myHomerHellOriented(
    colorSkin = "orange",
    colorEyes = "black",
    colorPupils = "white",
    colorHorns = "red",
    size = 100,  //diameter horizontal
    posX = 0,
    posY = 0,
    posZ = 0,
    directionX = 0,
    directionY = 0,
    directionZ = 1)
{
    let s = size / 100; //scaling factor
    let vHomerHellDirection = createVector(
        directionX, 
        directionY, 
        directionZ);
    vHomerHellDirection.normalize();
    let theta = atan2(vHomerHellDirection.x, vHomerHellDirection.z);
    let phi = asin(vHomerHellDirection.y);

    push();
    translate(posX, posY, posZ);
    rotateY(theta);
    rotateX(-phi);
    scale(s, s, s);
    myHomerHell(colorSkin, colorEyes, colorPupils, colorHorns);
    pop();
}

function myHealItem(x, y, z) {
    push();
    translate(x, y + cos((frameCount * 10) % 360) * 10, z);
    fill("green");
    scale(30, 30, 30);
    rotateY(frameCount % 360)
    box(1, 1, 1)
    pop();
}

function myPurgeItem(x, y, z) {
    push();
    translate(x, y + cos((frameCount * 10) % 360) * 10, z);
    fill("red");
    scale(30, 30, 30);
    rotateY(frameCount % 360)
    box(1, 1, 1)
    pop();
}

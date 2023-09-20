/// <reference path="../p5.global-mode.d.ts" />

var box1 = new Box(0, 0, 0, 0, 0, 0, 10, 10, 10);

function setup() {

    console.log("Inside setup..");
    createCanvas(750, 500, WEBGL);
    frameRate(30);
    debugMode();
    //box1 = new Box(0, 0, 0, 0, 0, 0, 10, 10, 10);

}

function draw() {
    orbitControl();
    background("grey");
    writeStuff();

    box1.draw();



    //window.box1.draw();
    box1.sx = (frameCount / 4) % 20
    box1.sz = (frameCount / 4) % 20
        //box()



}

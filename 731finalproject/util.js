/////////////////////////////////////////
// Author: Nikolas HEISE
// Date: 26.3.2021
/////////////////////////////////////////

// THIS IS A UTIL FILE TO CLEANUP THE WORKSPACE
// I refactored some functions to get rid of 'my' infront of the function

//----------------------------------------------------
// pre713
// Basic function provided by teacher to simplify
// generation of random numbers
function randBetween(a, b) {
    Math.random;
    let low, high;
    if (a <= b) {
        low = a;
        high = b;
    }
    else {
        low = b;
        high = a;
    }
    let range = high - low + 1;
    let n;
    n = low + (Math.floor(Math.random() * range));
    return (n);
} //myRandom

/////////////////////////////////////////
// FUNCTIONS PROVIDED BY THE TEACHER previous activities
// + myAxes

//--------------------------------------------
// Paints the X,Y,Z axes
// X: Red, thicker/lighter the positive axis
// Y: Green
// Z: Blue
// Addionally, a grid on the plane XZ
function debugAxes() {
    // Paint AXIS X
    strokeWeight(8);
    stroke("red");
    line(0, 0, 0,    750, 0, 0);
    strokeWeight(2);
    stroke("darkred");
    line(0, 0, 0,    -750, 0, 0);
    
    // Paint AXIS Y
    strokeWeight(8);
    stroke("green");
    line(0, 0, 0,    0, 500, 0);
    strokeWeight(2);
    stroke("darkgreen");
    line(0, 0, 0,    0, -500, 0);
    
    // Paint AXIS Z
    strokeWeight(8);
    stroke("blue");
    line(0, 0, 0,    0, 0, 500);
    strokeWeight(2);
    stroke("darkblue");
    line(0, 0, 0,    0, 0, -500);
    
    // optionally, some grid of axes....
    stroke("black");
    strokeWeight(1);
 
    // "verticals"
    for(let x = -700; x <=700; x = x + 50){
        line(x, 0, -700,     x, 0, 700); 
    }
    // "horizontals"
    for(let z = -700; z <=700; z = z + 50){
        line(700, 0, z,     -700, 0, z); 
    }
    
}//myAxes

function countEnemies(list) {
    let count = 0;
    list.forEach(element => {
        if (element.objectType !== "enemyBullet") {
            count++;
        }
    });
    return count;
}

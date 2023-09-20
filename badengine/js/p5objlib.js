var Preset = {
    BOX: 'box',
    SPHERE: 'sphere',
    PLAYER: 'player',
    CONTROLLER: 'controller',
    CAMERA: 'camera'
        //TODO: Add more
};

var DupePolicy = {
    ALLOWED: 'allowed', //      ALLOWED; nothing will be done. Controllers will be executed in order of their ids
    WARN: 'warn', //            WARN; Console Warning will be executed. Controllers will be executed in order of their ids
    ERROR: 'error' //           ERROR; Console throws an ERROR and the application does not work
           
};

function LibModule(name, dupePolicy, type) {
    //  Modules are Objects that are added to a LibObject to create its functionality
    //  Can be anything from a Shape modifier to a Material or even Positional Data from the TransformationController
    "use strict";
    // The Custom Name of the Module
    this.customName = name;
    
    // If the controller should be activated. Deactivate for storage and activate later
    this.activated = true;
    
    // The Type of the Objec.Used for duplicate Checking
    this.type = type;
    
    // dupePolicy: string ={"ALLOWED", "WARN", "ERROR"}; is it possible to have multiple activated Controllers of the same type assigned to the SceneObject
    this.dupePolicy = dupePolicy;
    
    
    // Update
    this.update = function () {};
    
    
}


function LibObject(name) {
    "use strict";
    // The Identification Name of the Object
    this.name = name;
    
    // Array of modules in order of being added via addModule
    this.moduleList = [];
    
    // Is the Objct ready to be drawn. Modify at own risk. Use init() method instead for Legitimacy Check
    this.isReady = false;

    // Apply a Preset to the Object. Clears all modules and adds new Modulesaccording to the preset chosen
    this.applyPreset = function (p) {
        this.moduleList = []; // TODO:
        return this;
    };
    
    // Add a module to the Object. Adding a LibObject itself does nothing
    this.addModule = function (mod) {
        var i;
        if (mod.hasOwnProperty('type')) {
            for (i = 0; i < this.moduleList.length; i += 1) {
                if (this.moduleList[i].type === mod.type) {
                    if (this.moduleList[i].dupePolicy === DupePolicy.ERROR) {
                        throw "A Module of the type: " + mod.type + "allready exists. This error was called because the dupePolicy of the module is set to ERROR";
                    } // TODO: Add warn case
                }
            }
            this.moduleList.push(mod);
        } else {
            throw "Parameter " + mod + " is not an LibModule";
        }
        return this;
    };
    
    // Get a module by its name
    this.getModule = function (name) {
        var i;
        for (i = 0; i <= this.moduleList.length; i += 1) {
            if (this.moduleList[i].customName === name) {
                return this.moduleList[i];
            }
        }
    };

    // Update function. Call in your draw method
    this.update = function () {
        var i;
        if (this.isReady) {
            push();
            for (i = 0; i < this.moduleList.length; i += 1) {
                this.moduleList[i].update();
            }
            //pop();
        }
    };
    
    // Finalizes the Object and makes it ready to be drawn
    this.makeReady = function () {
        // TODO: Add Checks
        this.isReady = true;
    };
    
}





function PositionModule(name, x, y, z) {
    "use strict";
    LibModule.call(this, name, DupePolicy.ERROR, "PositionModule");
    
    this.x = x;
    this.y = y;
    this.z = z;
    
    this.update = function () {
        translate(this.x, this.y, this.z);
    };
}

function RotationModule(name, x, y, z) {
    "use strict";
    LibModule.call(this, name, DupePolicy.ERROR, "RotationModule");
    
    this.x = x;
    this.y = y;
    this.z = z;
    
    this.update = function () {
        // TODO: Add Anglemode
        rotate(this.x, this.y, this.z);
    };
}

// TODO: add vectorbased corresponding Objects

function ScaleModule(name, x, y, z) {
    "use strict";
    LibModule.call(this, name, DupePolicy.ERROR, "ScaleModule");
    
    this.x = x;
    this.y = y;
    this.z = z;
    
    this.update = function () {
        //console.log(this.x, this.y, this.z);
        scale(this.x, this.y, this.z);
    };
}



function SimpleShapeModule(name, shapePreset, multiplier) {
    "use sctrict";
    LibModule.call(this, name, DupePolicy.ERROR, "SimpleShapeModule");
    
    this.shape = shapePreset;
    this.multiplier = multiplier;
    
    
    this.update = function () {
        if (this.shape === ("Box")) {
            //ambientLight(0, 255, 0);
            //ambientMaterial(255, 0, 255);
            
            box(multiplier); //TODO: Add more
        }
    };
}

var MatType = {
    NORMAL: 'normal',
    EMISSIVE: 'emissive',
    SPECULAR: 'specular',
    AMBIENT: 'ambient'
};

// args: {colour, texturePath, }
function MaterialModule(name, type, args) {
    "use strict";
    LibModule.call(this, name, DupePolicy.ERROR, "MaterialModule");
    //this.texture = loadImage(texture);
    this.type = type;
    
    if (args.colour) {
        this.colour = args.colour;
        //console.log(args.colour)
    } else if (args.texture) {
        this.texture = args.texture;
    }
    
    this.update = function () {
        //texture(texture);
        //colorMode(RGB, 255);
        //console.log(this.colour);
        fill(this.colour);
        if (this.type === MatType.NORMAL) {
            normalMaterial(this.colour);
        } else if (this.type === MatType.EMISSIVE) {
            emissiveMaterial(this.colour);
        } else if (this.type === MatType.SPECULAR) {
            specularMaterial(this.colour);
        } else if (this.type === MatType.AMBIENT) {
            ambientMaterial(this.colour);
        }
        //shininess(this.shininess);
    }
    
}

// Module List:
// VectorBased: Context: 2D or 3D
//  Position:       Check Context of others
//  Rotation:       Check Context of others
//  Scale:          Check Context of others
// Material
//  texture
//  color
//  type
// LightSource
//  ambience
//  point
//  directional
// Physics
//  Collision
//  Gravity
// Camera
// Control
// Script

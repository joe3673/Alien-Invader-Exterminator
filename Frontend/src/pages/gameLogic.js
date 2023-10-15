var playerCells = Array.from(document.getElementsByClassName('.playerCell'));
const alienCells = document.querySelectorAll(".alienCell");

const statusText = document.querySelector("#statusText");
const restartButton = document.querySelector("#restartButton");
const exitButton = document.querySelector("#exitButton");

let playerLocations = [];
let alienLocations = [];
let currentPlayer = "X";
let running = false;

initializeGame();

function initializeGame() {
    console.log("started");
    console.log(playerCells);
    console.log(alienCells);
    //playerCells.forEach(cell => cell.addEventListener("click", placeShip));
    //alienCells.forEach(cell => cell.addEventListener("click", placeShip));

    //restartButton.addEventListener("click", restartGame);
    //exitButton.addEventListener("click", exitGame);
    //statusText.textContent = `${currentPlayer}'s turn`;

    running = true;
}
function placeShip() {
    console.log("clicked");
    //const cellIndex = this.getAttribute("cellIndex");

    //updateCell(this, cellIndex);
    //const cellIndex = this.getAttribute("cellIndex");
    //const coordinates = cellIndex.split('-');

    //const x = coordinates[0];
    //const y = coordinates[1];

    //this.style.backgroundColor = 'black';

    //changeShipCells(this, x, y);
}
function updateCell() {
    this.textContent = currentPlayer;
}
function changeShipCells(cell, x, y) {
    let x2 = x;
    let y2 = y;
    //let cell = x2 + '-' + y2;

    cell.setAttribute('style', 'background-color: #000000');

    document.getElementById(cell).style.backgroundColor='#000000';

    x2 ++;

    document.getElementById(cell).style.backgroundColor='#000000';

    x2 ++;

    document.getElementById(cell).style.backgroundColor='#000000';

    x2 = x;
    y2 ++;

    document.getElementById(cell).style.backgroundColor='#000000';

    x2 ++;

    document.getElementById(cell).style.backgroundColor='#000000';

    x2 ++;

    document.getElementById(cell).style.backgroundColor='#000000';
}

function shootCell() {
    const cellIndex = this.getAttribute("cellIndex");

    if (alienLocations.includes(cellIndex)) {

    }
}
function playerAttack(x, y) {
    if (alienGrid[y][x] == 'O') {
        alienGrid[y][x] = '!';

        let element = x + '-' + y;
        playerShots.push(element);

        return true;

    } else if (alienGrid[y][x] == '-') {
        alienGrid[y][x] = 'x';

        let element = x + '-' + y;
        playerShots.push(element);

        return false;

    } else {

        return false;
    }
}
function updateCell(cell, index) {

}
function changePlayer() {

}
function checkWinner() {

}
function restartGame() {

}
function exitGame() {

}


/*

let playerGrid = createGrid(5, 10);
let alienGrid = createGrid(5, 10);
let playerHealth = 12;
let alienHealth = 12;

const playerShots = [];
const alienShots = [];

printGrid(playerGrid);
printGrid(alienGrid, true);

//Placing both player ships and both alien ships
for (let i = 1; i < 3; i++) {
    let x = prompt('Enter the x coordinate for your ship #' + i);
    let y = prompt('Enter the y coordinate for your ship #' + i);

    placeShip(x, y, 'O', playerGrid);
    placeAlienShip('O', alienGrid, 3, 9);

    drawBreak();
    printGrid(playerGrid);
    printGrid(alienGrid, true);
}

//Game Logic
while(alienHealth > 0 && playerHealth > 0) {
    let x = prompt('Enter the x coordinate for your attack');
    let y = prompt('Enter the y coordinate for your attack');

    while(playerShots.includes(x + '-' + y)) {
        console.log('You\'ve already fired there. Choose another cell');

        x = prompt('Enter the x coordinate for your attack');
        y = prompt('Enter the y coordinate for your attack');
    }

    if (playerAttack(x, y, alienGrid)) {
        alienHealth--;
    }

    if (alienHealth > 0 && alienAttack()) {
        playerHealth--;
    }

    drawBreak();
    printGrid(playerGrid);
    printGrid(alienGrid, true);
}

if (playerHealth < alienHealth) {
    console.log('YOU LOST!');
} else {
    console.log('YOU WON!')
}



function placeAlienShip(c, grid, adjustedLength, adjustedHeight) {
    let didPlace = false;

    while (!didPlace) {
        let x = getRandomInt(adjustedLength);
        let y = getRandomInt(adjustedHeight);

        if (!alienLocations[`${x}-${y}`]) {
            placeShip(x, y, c, grid);
            didPlace = true;
            fixAlienLocations(x, y);
        }
    }
}
function fixAlienLocations(x, y) {
    let x2 = x;
    let y2 = y;

    alienLocations[`${x2}-${y2}`] = true;

    x2 ++;

    alienLocations[`${x2}-${y2}`] = true;

    x2 ++;

    alienLocations[`${x2}-${y2}`] = true;

    x2 = x;
    y2 ++;

    alienLocations[`${x2}-${y2}`] = true;

    x2 ++;

    alienLocations[`${x2}-${y2}`] = true;

    x2 ++;

    alienLocations[`${x2}-${y2}`] = true;
}

function playerAttack(x, y) {
    if (alienGrid[y][x] == 'O') {
        alienGrid[y][x] = '!';

        let element = x + '-' + y;
        playerShots.push(element);

        return true;

    } else if (alienGrid[y][x] == '-') {
        alienGrid[y][x] = 'x';

        let element = x + '-' + y;
        playerShots.push(element);

        return false;

    } else {

        return false;
    }
}
function alienAttack() {
    let x = getRandomInt(5);
    let y = getRandomInt(10);

    let result = alienShots.includes(x + '-' + y);

    while(result) {
        x = getRandomInt(5);
        y = getRandomInt(10);

        result = alienShots.includes(x + '-' + y);
    }

    if (playerGrid[y][x] == 'O') {
        playerGrid[y][x] = '!';

        let element = x + '-' + y;
        alienShots.push(element);

        return true;

    } else if (playerGrid[y][x] == '-') {
        playerGrid[y][x] = 'x';

        let element = x + '-' + y;
        alienShots.push(element);

        return false;

    } else {
        return false;
    }
}

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}

function createGrid(length, height) {
    let grid = [];

    for (let i = 0; i < height; i++) {
        grid[i] = [];

        for (let j = 0; j < length; j++) {
            grid[i][j] = '-';
        }
    }

    return grid;
}

function printGrid(grid, isAlien = false) {
    const headers = createHeaders(5);
    console.log(headers);

    for (let i = 0; i < grid.length; i++) {
        let rowString = i + ' ';

        for (let cell of grid[i]) {
            if (isAlien && cell == 'O') {
                rowString += '- ';

            } else {
                rowString += cell + ' ';

            }
        }
        console.log(rowString);
    }
}

function createHeaders(length) {
    let result = '  ';

    for (let i = 0; i < length; i++) {
        result += i + ' ';
    }

    return result;
}

function drawBreak() {
    console.log('----------------------');
}

*/
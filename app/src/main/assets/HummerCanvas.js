const canvas = new Canvas();
const ctx = canvas.getContext("2d");

// Draw rect
ctx.rect(20, 20, 150, 100);
ctx.fill();

// Draw text
ctx.fillStyle = "black";
ctx.font = "20px serif"
ctx.fillText("Hello World", 200, 50);

// Draw rect
ctx.fillColor = "red";
ctx.fillRect(10, 10, 150, 100);

// First path
ctx.beginPath();
ctx.lineWidth = 2;
ctx.strokeStyle = "blue";
ctx.moveTo(20, 20);
ctx.lineTo(200, 20);
ctx.stroke();

// Second path
ctx.beginPath();
ctx.fillStyle = "green";
ctx.moveTo(20, 20);
ctx.lineTo(120, 120);
ctx.lineTo(20, 120);
ctx.closePath();
ctx.fill();

// stroke rect
ctx.strokeStyle = "green";
ctx.strokeRect(10, 150, 160, 100);

// stroke text
ctx.strokeStyle = "blue";
ctx.lineWidth = 2;
ctx.font = "50px serif";
ctx.strokeText("Hello world", 50, 190, 500);

Hummer.render(canvas);
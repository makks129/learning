const express = require('express');
const app = express();

app.get('/load-cpu', (_req, res) => {
	const start = Date.now();
	const result = loadCpu();
	const end = Date.now();
	console.log(`Computation took ${end - start}ms`);
	res.json({ result });
});

function loadCpu() {
	let sum = 0;
	for (let i = 0; i < 100_000_000; i++) {
		sum += Math.pow(Math.sin(i), i);
	}
	return sum;
}

const data = [];

app.get('/memory-leak', (_req, res) => {
	setInterval(() => {
		data.push(new Array(10_000_000).join('a'));
		console.log('leaking...');
	}, 100);
	res.send('Memory leak started!');
});

app.listen(3000, () => {
	console.log('Server started on port 3000');
});

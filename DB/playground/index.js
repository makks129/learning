const knex = require('knex')({
	client: 'mysql',
	connection: {
		host: process.env.HOST,
		port: 3306,
		user: process.env.DB_USER,
		password: process.env.DB_PWD,
		database: process.env.DB_NAME,
	},
});

let run;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

async function x() {
	const row1 = {
		company_id: 0,
		user_id: 1123123,
		name: 'Some Rule',
		description: 'Some description of a rule',
		rule: '{"some":"random","long":[{"verylong":1,"object":2}],"long":[{"verylong":1,"object":2}],"long":[{"verylong":1,"object":2}],"long":[{"verylong":1,"object":2}]}',
		rule_schema:
			'{"some":"random","long":[{"verylong":1,"object":2}],"long":[{"verylong":1,"object":2}],"long":[{"verylong":1,"object":2}],"long":[{"verylong":1,"object":2}]}',
		team_id: '1',
		order: 0,
		active: 1,
		update_by: '0',
	};

	for (let i = 0; i < 50; i++) {
		console.log(`# ${i}`);
		let query =
			'INSERT INTO rules (`company_id`, `user_id`, `name`, `description`, `rule`, `rule_schema`, `team_id`, `order`, `active`, `update_by`) VALUES ';
		for (let j = 0; j < 10000; j++) {
			const cid = randomIntOf(100000);
			console.log(`   > ${j}, cid: ${cid}`);
			row1.company_id = `${cid}`;
			row1.active = randomIntOf(1);
			query += `('${row1.company_id}', '${row1.user_id}', '${row1.name}', '${row1.description}', '${row1.rule}', '${row1.rule_schema}', '${row1.team_id}', ${row1.order}, ${row1.active}, '${row1.update_by}'),\n`;
		}
		query = query.substring(0, query.length - 2);
		query += ';';
		await knex.raw(query);
	}

	const res2 = await knex('rules').count('* as count');
	console.log(res2);
}

function randomIntOf(int) {
	return Math.round(Math.random() * int);
}

run = async function y() {
	let query = 'INSERT IGNORE INTO registered_companies (`company_id`, `active`) VALUES ';
	for (let i = 0; i < 100000; i++) {
		query += `('${i}', 1),\n`;
	}
	query = query.substring(0, query.length - 2);
	query += ';';
	await knex.raw(query);
};

async function p() {
	const query = `SELECT
	rc.company_id,
	rc.active,
	SUM(IF(r.active = 1, 1, 0)) as active_rules_sum
    FROM registered_companies rc
    JOIN rules r
    ON rc.company_id = r.company_id
    GROUP BY r.company_id
    HAVING active_rules_sum = 1`;

	console.time('x');
	const res = await knex.raw(query);
	console.log(res);
	console.timeEnd('x');
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

(async () => {
	try {
		await run();
		process.exit(0);
	} catch (error) {
		console.error(error);
		process.exit(1);
	}
})();

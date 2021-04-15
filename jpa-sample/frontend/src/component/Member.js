import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';

const Member = () => {
	const [data, setData] = useState([]);

	useEffect(async () => {
		try {
			const result = await axios.get('/api/v1/member');
			console.log(result.data);
			setData(result.data);
		} catch (e) {
			console.log(e);
		}
	}, []);
	return (
		<>
			<Table striped bordered hover>
				<thead>
					<tr>
						<th>id</th>
						<th>name</th>
						<th>city</th>
						<th>street</th>
						<th>zipcode</th>
					</tr>
				</thead>
				<tbody>
					{data.map(({ id, name, address }) => (
						<tr key={id + name + address}>
							<td>{id}</td>
							<td>{name}</td>
							<td>{address.city}</td>
							<td>{address.street}</td>
							<td>{address.zipcode}</td>
						</tr>
					))}
				</tbody>
			</Table>
		</>
	);
};

export default Member;

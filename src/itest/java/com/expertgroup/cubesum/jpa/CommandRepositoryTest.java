package com.expertgroup.cubesum.jpa;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.expertgroup.cubesum.dto.CommandResponse;
import com.expertgroup.cubesum.entity.Command;
import com.expertgroup.cubesum.helper.CubeSumHelper;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommandRepositoryTest {
	@Autowired
	private CommandRepository commandRepository;
	@InjectMocks
	private CubeSumHelper cubeSumHelper;
	private static String commands = "2\r\n" + 
			"4 5\r\n" + 
			"UPDATE 2 2 2 4\r\n" + 
			"QUERY 1 1 1 3 3 3\r\n" + 
			"UPDATE 1 1 1 23\r\n" + 
			"QUERY 2 2 2 4 4 4\r\n" + 
			"QUERY 1 1 1 3 3 3\r\n" + 
			"2 4\r\n" + 
			"UPDATE 2 2 2 1\r\n" + 
			"QUERY 1 1 1 1 1 1\r\n" + 
			"QUERY 1 1 1 2 2 2\r\n" + 
			"QUERY 2 2 2 2 2 2";

	@Test
	public void testFindAll() {
		Iterable<Command> findAll = commandRepository.findAll();
		assertNotNull(findAll);
	}

	@Test
	public void testProcessCommand() {
		CommandResponse cmdResponse = new CommandResponse();
		String[] cmds = commands.split("\n");
		Command command = new Command();
		command.setId(1L);
		command.setCmd(commands);
		commandRepository.save(command);		
		cmdResponse.setResult(cubeSumHelper.process(cmds));
		assertNotNull(cmdResponse.getResult());
	}
}

package com.expertgroup.cubesum.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.expertgroup.cubesum.entity.Command;
import com.expertgroup.cubesum.helper.CubeSumHelper;
import com.expertgroup.cubesum.jpa.CommandRepository;

@CrossOrigin
@RestController
public class CubeSumController {
	private static final Logger log = LogManager.getLogger(CubeSumController.class);
	@Autowired
	private CommandRepository commandRepository;
	@Autowired
	private CubeSumHelper cubeSumHelper;

	@GetMapping("/process")
	public Iterable<Command> getPedido() {
		return commandRepository.findAll();
	}

	@PostMapping(name = "/process", consumes = "text/plain", produces = "text/plain")
	public @ResponseBody String process(@RequestBody String nameCmds) {
		String[] cmds = nameCmds.split("\n");
		log.info("Request: /process" + nameCmds);
		try {
			Command command = new Command();
			command.setId(1L);
			command.setCmd(nameCmds);
			commandRepository.save(command);
		} catch (Exception e) {
			log.error(e);
		}
		return cubeSumHelper.process(cmds);
	}
}

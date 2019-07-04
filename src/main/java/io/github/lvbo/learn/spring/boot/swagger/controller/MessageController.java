
package io.github.lvbo.learn.spring.boot.swagger.controller;

import io.github.lvbo.learn.spring.boot.swagger.controller.response.CommonResponse;
import io.github.lvbo.learn.spring.boot.swagger.repository.MessageRepository;
import io.github.lvbo.learn.spring.boot.swagger.model.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lvbo
 */
@Api(tags = {"消息管理"}, protocols = "http")
@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;

	@ApiOperation(
			value = "${MessageController.list.value}",
			notes = "${MessageController.list.notes}",
			produces="application/json, application/xml",
			consumes="application/json, application/xml")
	@GetMapping()
	public List<Message> list() {
		List<Message> messages = this.messageRepository.findAll();
		return messages;
	}

	@ApiOperation(
			value = "${MessageController.create.value}",
			notes = "${MessageController.cteate.notes}"
	)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "消息 ID", required = true, dataType = "long", paramType = "query"),
			@ApiImplicitParam(name = "text", value = "正文", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "summary", value = "摘要", dataType = "String", paramType = "query"),
	})
	@PostMapping()
	public Message create(Message message) {
		message = this.messageRepository.save(message);
		return message;
	}

	@ApiOperation(
			value = "修改消息",
			notes = "根据参数修改消息"
	)
	@ApiResponses({
			@ApiResponse(code = 100, message = "请求参数有误"),
			@ApiResponse(code = 101, message = "未授权"),
			@ApiResponse(code = 103, message = "禁止访问"),
			@ApiResponse(code = 104, message = "请求路径不存在"),
	})
	@PutMapping()
	public Message modify(Message message) {
		Message messageResult=this.messageRepository.update(message);
		return messageResult;
	}

	@PatchMapping()
	public CommonResponse<Message> patch(Message message) {
		Message messageResult=this.messageRepository.updateText(message);
		return CommonResponse.successWithData(messageResult);
	}

	@ApiImplicitParam(name = "id", value = "消息 ID", required = true, dataType = "long", paramType = "query")
	@GetMapping(value = "{id}")
	public Message get(@PathVariable Long id) {
		Message message = this.messageRepository.findMessage(id);
		return message;
	}

	@ApiImplicitParam(name = "id", value = "消息 ID", required = true, dataType = "long", paramType = "query")
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable("id") Long id) {
		this.messageRepository.deleteMessage(id);
	}
}

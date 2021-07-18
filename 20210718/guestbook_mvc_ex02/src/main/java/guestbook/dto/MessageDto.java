package guestbook.dto;

import java.time.LocalDateTime;

// 디비에서 데이터를 가져오거나 넣어줄 때
// 데이터를 담는 역할을 함
// Data transfer object 
// 화면에 뿌려줄 데이타를 담는 아이!
// 계층간에 데이타를 전달하는 역할 예) controller -> service

public class MessageDto {
	private int messageId;
	private String guestName;
	private String message;
	private LocalDateTime writeDate;
	private LocalDateTime updateDate;
	
	
	public MessageDto() {
		
	}
	
	public MessageDto(int messageId, String guestName, String message, LocalDateTime writeDate,
			LocalDateTime updateDate) {
		super();
		this.messageId = messageId;
		this.guestName = guestName;
		this.message = message;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
	}
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(LocalDateTime writeDate) {
		this.writeDate = writeDate;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "MessageDto [messageId=" + messageId + ", guestName=" + guestName + ", message=" + message
				+ ", writeDate=" + writeDate + ", updateDate=" + updateDate + "]";
	}	
	
}

package guestbook.dto;

import java.util.List;

// 화면에 보여줄 데이타
// 1.메시지의 리스트 
// 2.페이징 처리할 데이타
public class MessageListViewDto {
	private int messageTotalCount; // 메시지 전체 개수
	private int page; //현재 페이지 번호
	private List<MessageDto> messageList; // 한 페이지(현재 페이지) 에서 보여줄 메시지 리스트 
	private int pageTotalCount; // 전체페이지 개수
	private int messageCountPerPage; // 페이지당 메시지 개수
	private int firstRow; // 화면상 맨위에 있는 row 번호	
	public MessageListViewDto(int messageTotalCount, int page, List<MessageDto> messageList, int messageCountPerPage,
			int firstRow) {
		super();
		this.messageTotalCount = messageTotalCount;
		this.page = page;
		this.messageList = messageList;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = firstRow;
		
		// 전체 페이지 갯수는 구함
		if(messageCountPerPage == 0) {
			pageTotalCount = 0;
		}else {
			pageTotalCount = (int)Math.ceil((double)messageTotalCount/ messageCountPerPage);
		}
	}
	
	
	
	public MessageListViewDto() {
		super();
	}



	public int getMessageTotalCount() {
		return messageTotalCount;
	}
	public int getPage() {
		return page;
	}
	public List<MessageDto> getMessageList() {
		return messageList;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public int getMessageCountPerPage() {
		return messageCountPerPage;
	}
	public int getFirstRow() {
		return firstRow;
	}



	@Override
	public String toString() {
		return "MessageListViewDto [messageTotalCount=" + messageTotalCount + ", page=" + page + ", messageList="
				+ messageList + ", pageTotalCount=" + pageTotalCount + ", messageCountPerPage=" + messageCountPerPage
				+ ", firstRow=" + firstRow + "]";
	}
	
	
	
	
}

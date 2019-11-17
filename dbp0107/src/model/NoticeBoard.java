package model;

// board/notice/{departement_no}/{board_no}
public class NoticeBoard {
	private int board_no;
	private String title;
	private String contents;
	private String createtime;
	private int department_no; // 학과별 게시판이기 때문에 check하는 용도
	private String writer; // 게시글 글쓴이
	// private int customer_no;

	public NoticeBoard(int board_no, String title, String contents, String createtime, int department_no,
			String writer) {
		super();
		this.board_no = board_no;
		this.title = title;
		this.contents = contents;
		this.createtime = createtime;
		this.department_no = department_no;
		this.writer = writer;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getDepartment_no() {
		return department_no;
	}

	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "NoticeBoard [board_no=" + board_no + ", title=" + title + ", contents=" + contents + ", createtime="
				+ createtime + ", department_no=" + department_no + ", writer=" + writer + "]";
	}

	
}

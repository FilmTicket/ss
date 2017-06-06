package easybuy.server.model;

public class UserInfo {
	private Integer userId;
    private String userName;
    private String description;
    private String avatar;
    
	public UserInfo() {
		super();
	}

	public UserInfo(String userName, String description, String avatar) {
		super();
		this.userName = userName;
		this.description = description;
		this.avatar = avatar;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}

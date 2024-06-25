package org.youtube_kstream.Model;

import java.util.Objects;

public class VideoData {
	
	private String title;
	private Integer likes;
    private Integer views;
    private Integer comments;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoData videoData = (VideoData) o;
		return Objects.equals(likes, videoData.likes) &&
		Objects.equals(views, videoData.views) &&
		Objects.equals(comments, videoData.comments) &&
		Objects.equals(title, videoData.title);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, likes, views, comments);
    }
}

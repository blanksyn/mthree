package com.mthree;

public class DVD {
	private int id;
	private String title;
	private String releaseDate;
	private double mpaaRating;
	private String directorName;
	private String studio;
	private String note;
	
	public DVD() {
		
	}

	public DVD(int id,String title, String releaseDate, double mpaaRating, String directorName, String studio,
			String note) {
//		super();
		this.id=id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.mpaaRating = mpaaRating;
		this.directorName = directorName;
		this.studio = studio;
		this.note = note;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getMpaaRating() {
		return mpaaRating;
	}

	public void setMpaaRating(double mpaaRating) {
		this.mpaaRating = mpaaRating;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "DVD [id=" + id + ", title=" + title + ", releaseDate=" + releaseDate + ", mpaaRating=" + mpaaRating
				+ ", directorName=" + directorName + ", studio=" + studio + ", note=" + note + "]";
	}

	
}

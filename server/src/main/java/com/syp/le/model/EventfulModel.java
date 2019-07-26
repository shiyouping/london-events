package com.syp.le.model;

import java.util.List;

import org.pojomatic.annotations.AutoProperty;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since 26 Jul 2019
 */
@AutoProperty
public class EventfulModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "total_items")
	private Integer totalItems;
	@JSONField(name = "page_size")
	private Integer pageSize;
	@JSONField(name = "page_count")
	private Integer pageCount;
	@JSONField(name = "page_number")
	private Integer pageNumber;
	@JSONField(name = "page_items")
	private Integer pageItems;
	@JSONField(name = "first_item")
	private Integer firstItem;
	@JSONField(name = "last_item")
	private Integer lastItem;
	@JSONField(name = "search_time")
	private Float searchTime;
	@JSONField(name = "events")
	private EventsModel events;

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageItems() {
		return pageItems;
	}

	public void setPageItems(Integer pageItems) {
		this.pageItems = pageItems;
	}

	public Integer getFirstItem() {
		return firstItem;
	}

	public void setFirstItem(Integer firstItem) {
		this.firstItem = firstItem;
	}

	public Integer getLastItem() {
		return lastItem;
	}

	public void setLastItem(Integer lastItem) {
		this.lastItem = lastItem;
	}

	public Float getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(Float searchTime) {
		this.searchTime = searchTime;
	}

	public EventsModel getEvents() {
		return events;
	}

	public void setEvents(EventsModel events) {
		this.events = events;
	}

	@AutoProperty
	public static class EventsModel extends BaseModel {

		private static final long serialVersionUID = 1L;

		private List<EventModel> event;

		public List<EventModel> getEvent() {
			return event;
		}

		public void setEvent(List<EventModel> event) {
			this.event = event;
		}
	}

	@AutoProperty
	public static class EventModel extends BaseModel {

		private static final long serialVersionUID = 1L;

		@JSONField(name = "url")
		private String url;
		@JSONField(name = "title")
		private String title;
		@JSONField(name = "description")
		private String description;
		@JSONField(name = "start_time")
		private String startTime;
		@JSONField(name = "stop_time")
		private String stopTime;
		@JSONField(name = "venue_id")
		private String venueId;
		@JSONField(name = "venue_url")
		private String venueUrl;
		@JSONField(name = "venue_name")
		private String venueName;
		@JSONField(name = "venue_display")
		private String venueDisplay;
		@JSONField(name = "venue_address")
		private String venueAddress;
		@JSONField(name = "city_name")
		private String cityName;
		@JSONField(name = "region_name")
		private String regionName;
		@JSONField(name = "region_abbr")
		private String regionAbbreviation;
		@JSONField(name = "postal_code")
		private String postalCode;
		@JSONField(name = "country_name")
		private String countryName;
		@JSONField(name = "all_day")
		private Integer allDay;
		@JSONField(name = "latitude")
		private Float latitude;
		@JSONField(name = "longitude")
		private Float longitude;
		@JSONField(name = "geocode_type")
		private String geocodeType;
		@JSONField(name = "trackback_count")
		private Integer trackbackCount;
		@JSONField(name = "calendar_count")
		private Integer calendarCount;
		@JSONField(name = "comment_count")
		private Integer commentCount;
		@JSONField(name = "link_count")
		private Integer linkCount;
		@JSONField(name = "created")
		private String creationTime;
		@JSONField(name = "owner")
		private String owner;
		@JSONField(name = "modified")
		private String modificationTime;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getStopTime() {
			return stopTime;
		}

		public void setStopTime(String stopTime) {
			this.stopTime = stopTime;
		}

		public String getVenueId() {
			return venueId;
		}

		public void setVenueId(String venueId) {
			this.venueId = venueId;
		}

		public String getVenueUrl() {
			return venueUrl;
		}

		public void setVenueUrl(String venueUrl) {
			this.venueUrl = venueUrl;
		}

		public String getVenueName() {
			return venueName;
		}

		public void setVenueName(String venueName) {
			this.venueName = venueName;
		}

		public String getVenueDisplay() {
			return venueDisplay;
		}

		public void setVenueDisplay(String venueDisplay) {
			this.venueDisplay = venueDisplay;
		}

		public String getVenueAddress() {
			return venueAddress;
		}

		public void setVenueAddress(String venueAddress) {
			this.venueAddress = venueAddress;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getRegionName() {
			return regionName;
		}

		public void setRegionName(String regionName) {
			this.regionName = regionName;
		}

		public String getRegionAbbreviation() {
			return regionAbbreviation;
		}

		public void setRegionAbbreviation(String regionAbbreviation) {
			this.regionAbbreviation = regionAbbreviation;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public Integer getAllDay() {
			return allDay;
		}

		public void setAllDay(Integer allDay) {
			this.allDay = allDay;
		}

		public Float getLatitude() {
			return latitude;
		}

		public void setLatitude(Float latitude) {
			this.latitude = latitude;
		}

		public Float getLongitude() {
			return longitude;
		}

		public void setLongitude(Float longitude) {
			this.longitude = longitude;
		}

		public String getGeocodeType() {
			return geocodeType;
		}

		public void setGeocodeType(String geocodeType) {
			this.geocodeType = geocodeType;
		}

		public Integer getTrackbackCount() {
			return trackbackCount;
		}

		public void setTrackbackCount(Integer trackbackCount) {
			this.trackbackCount = trackbackCount;
		}

		public Integer getCalendarCount() {
			return calendarCount;
		}

		public void setCalendarCount(Integer calendarCount) {
			this.calendarCount = calendarCount;
		}

		public Integer getCommentCount() {
			return commentCount;
		}

		public void setCommentCount(Integer commentCount) {
			this.commentCount = commentCount;
		}

		public Integer getLinkCount() {
			return linkCount;
		}

		public void setLinkCount(Integer linkCount) {
			this.linkCount = linkCount;
		}

		public String getCreationTime() {
			return creationTime;
		}

		public void setCreationTime(String creationTime) {
			this.creationTime = creationTime;
		}

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		public String getModificationTime() {
			return modificationTime;
		}

		public void setModificationTime(String modificationTime) {
			this.modificationTime = modificationTime;
		}
	}
}

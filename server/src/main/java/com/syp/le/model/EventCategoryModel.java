package com.syp.le.model;

import java.util.List;

import org.pojomatic.annotations.AutoProperty;

/**
 * 
 * Hold the information of Eventful event category API
 * 
 * @author <a href="mailto:ricky.shiyouping@gmail.com">Ricky Shi</a>
 *
 * @since Jul 26, 2019
 */
@AutoProperty
public class EventCategoryModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	private List<CategoryModel> category;

	public List<CategoryModel> getCategory() {
		return category;
	}

	public void setCategory(List<CategoryModel> category) {
		this.category = category;
	}

	@AutoProperty
	public static class CategoryModel extends BaseModel {

		private static final long serialVersionUID = 1L;

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}

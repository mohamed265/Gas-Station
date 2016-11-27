/**
 * Createed On: Oct 25, 2016 2:49:47 PM
 * Created By: mohamed.morsy
 */
package com.free.gasstation.dto;

/**
 * @author mohamed.morsy
 *
 */
public class City {
	private int id;

	private String name;

	private int contry_id;

	public City() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContryId() {
		return contry_id;
	}

	public void setContryId(int contry_id) {
		this.contry_id = contry_id;
	}
}

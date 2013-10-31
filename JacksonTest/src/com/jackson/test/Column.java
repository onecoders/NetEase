package com.jackson.test;

public class Column {

	private long _id;
	private int column_1;
	private String column_2;
	private String column_3;
	private String column_4;

	public Column() {

	}

	public Column(int column_1, String column_2, String column_3,
			String column_4) {
		this.column_1 = column_1;
		this.column_2 = column_2;
		this.column_3 = column_3;
		this.column_4 = column_4;
	}

	public long get_id() {
		return _id;
	}

	public Column set_id(long _id) {
		this._id = _id;
		return this;
	}

	public int getColumn_1() {
		return column_1;
	}

	public Column setColumn_1(int column_1) {
		this.column_1 = column_1;
		return this;
	}

	public String getColumn_2() {
		return column_2;
	}

	public Column setColumn_2(String column_2) {
		this.column_2 = column_2;
		return this;
	}

	public String getColumn_3() {
		return column_3;
	}

	public Column setColumn_3(String column_3) {
		this.column_3 = column_3;
		return this;
	}

	public String getColumn_4() {
		return column_4;
	}

	public Column setColumn_4(String column_4) {
		this.column_4 = column_4;
		return this;
	}

	@Override
	public String toString() {
		return "_id:" + get_id() + ",column_1:" + getColumn_1() + ",column_2:"
				+ getColumn_2() + ",column_3:" + getColumn_3() + ",column_4:"
				+ getColumn_4();
	}

}

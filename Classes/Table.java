public class Table {

	private int tableSize;
	private int tableID;
	private TableStatus tableStatus;

	public int getTableSize() {
		return this.tableSize;
	}

	/**
	 * 
	 * @param tableSize
	 */
	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}

	public int getTableID() {
		return this.tableID;
	}

	/**
	 * 
	 * @param tableID
	 */
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public void assignTableReservation() {
		// TODO - implement Table.assignTableReservation
		throw new UnsupportedOperationException();
	}

	public void unassignTableReservation() {
		// TODO - implement Table.unassignTableReservation
		throw new UnsupportedOperationException();
	}

}
class Mine(column: Int, row: Int) {
    private val board: Array<BooleanArray>
	
    init {
        board = Array(column) { BooleanArray(row) }
    }

    fun setMine(x: Int, y: Int) {
        board[x][y] = true
    }

    fun getMine(x: Int, y: Int): Boolean {
        return board[x][y]
    }

    val width: Int
        get() = board.size

    val height: Int
        get() = board[0].size

    fun countMine(x: Int, y: Int): Int {
        var count = 0
        for (i in x - 1..x + 1) {
            if (i < 0 || i >= width) {
                continue
            }
            for (j in y - 1..y + 1) {
                if (j < 0 || j >= height) {
                    continue
                }
                if (getMine(i, j)) {
                    count++
                }
            }
        }
        return count
    }
}
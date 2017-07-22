package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return if (year < other.year) -1
        else if(year > other.year) 1
        else if(year >= other.year && month > other.month) 1
        else if (month < other.month) -1
        else dayOfMonth.compareTo(other.dayOfMonth)
    }

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate): Iterable<MyDate>, ClosedRange<MyDate> {
    override fun iterator(): Iterator<MyDate> =
        DateIterator(this)

    class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
        var current: MyDate = dateRange.start
        override fun next(): MyDate {
            val result = current
            current = current.nextDay()
            return result
        }
        override fun hasNext(): Boolean = current <= dateRange.endInclusive
    }

    override fun contains(d: MyDate): Boolean = (d >= start && d <= endInclusive)
}
//Add an extension function 'times' to 'TimeInterval', constructing the value of this class.
//Add an extension function 'plus' to 'MyDate', taking a 'RepeatedTimeInterval' as an argument.
data class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

fun TimeInterval.times(i: Int) = RepeatedTimeInterval(this, i)

fun MyDate.plus(rti: RepeatedTimeInterval): MyDate =
        addTimeIntervals(rti.ti, rti.n)



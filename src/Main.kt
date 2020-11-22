fun main(args: Array<String>) {
    var employees = arrayListOf<Employee>(
            Employee("John", 15, "Support"),
            Employee("Anna", 25, "Analyst"),
            Employee("Dmitry", 19, "Engineer"))
    val aggregator = EmployeeAggregator(employees)
    aggregator.forEach { println(it) }
}

open class Person(name: String, age: Int)

data class Employee(val name: String, val age: Int, val position: String) : Person(name, age)

abstract class PersonAggregator<T: Person>(val persons: ArrayList<T>): Iterable<T>

class EmployeeAggregator(employees: ArrayList<Employee>): PersonAggregator<Employee>(employees) {
    override fun iterator(): MutableIterator<Employee> {
        return Iterator(persons);
    }
}

class Iterator(array: ArrayList<Employee>): MutableIterator<Employee> {

    private val filteredArray = array.filter { it.age > 16 }.toMutableList()
    private var pointer: Int = 0;

    override fun hasNext(): Boolean = pointer < filteredArray.size

    override fun next(): Employee {
        val current = filteredArray[pointer];
        pointer++;
        return current;
    }

    override fun remove() {
        val current = filteredArray[pointer];
        filteredArray.remove(current);
    }
}
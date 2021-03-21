import com.example.quizbuster.Question
import com.example.quizbuster.R

object Constants {
    const val USERNAME: String = "username"
    const val TOTAL_NUMBER_OF_QUESTIONS: String = "totalNumberOfQuestions"
    const val CORRECT_ANSWERS: String = "correctAnswers"
    const val SKIPPED_ANSWERS: String = "skippedAnswers"
    const val WRONG_ANSWERS: String = "wrongAnswers"
    fun getQuestions(): ArrayList<Question>{
        var questions = ArrayList<Question>()

        var options = ArrayList<String>()

//        Adding 1st Question
        options.add("Public method is accessible to all other classes in the hierarchy")
        options.add("Public method is accessible only to subclasses of its parent class")
        options.add("Public method can only be called by object of its class")
        options.add("Public method can be accessed by calling object of the public class")

        var question = Question(
                1,
                "Which of the statement is correct?",
                false,
                0,
                options,
                1,
                "None.",
                true,
                0,
                false
        )

        questions.add(question)

//        Adding 2nd Question
        options = ArrayList<String>()
        options.add("9")
        options.add("8")
        options.add("Compilation Error")
        options.add("Runtime Error")

        question = Question(2,
                "What will be the output of the following Java program?",
                true,
                R.drawable.ques2,
                options,
                3,
                "Two variables with the same name can’t be created in a class.",
                false,
                0,
                false
        )
        questions.add(question)

//        Adding 3rd Question
        options = ArrayList<String>()
        options.add("this")
        options.add("super")
        options.add("extends")
        options.add("final")

        question = Question(3,
                "Which of this keyword can be used in a subclass to call the constructor of superclass?",
                false,
                0,
                options,
                2,
                "The super keyword in Java is used in subclasses to access superclass members (attributes, constructors and methods).",
                false,
                0,
                false
        )
        questions.add(question)

//        Adding 4th Question
        options = ArrayList<String>()
        options.add("Method Overloading")
        options.add("Method Hiding")
        options.add("Method Overriding")
        options.add("None of the Above")
        question = Question(4,
                "What is the process of defining a method in a subclass having same name & type signature as a method in its superclass?",
                false,
                0,
                options,
                3,
                "None.",
                false,
                0,
                false
        )
        questions.add(question)

//        Adding 5th Question
        options = ArrayList<String>()
        options.add("2")
        options.add("Compilation Error")
        options.add("3")
        options.add("7")
        question = Question(5,
                "What will be the output of the following Java program?",
                true,
                R.drawable.ques5,
                options,
                4,
                "Both x,and y are pointing to the same array.",
                false,
                0,
                false
        )
        questions.add(question)

//        Adding 6th Question
        options = ArrayList<String>()
        options.add("abstract")
        options.add("extends")
        options.add("abstract and extends")
        options.add("None of the Above")
        question = Question(6,
                "Which of these keywords cannot be used for a class which has been declared final?",
                false,
                0,
                options,
                1,
                "A abstract class is incomplete by itself and relies upon its subclasses to provide a complete implementation. If we declare a class final then no class can inherit that class, an abstract class needs its subclasses hence both final and abstract cannot be used for a same class."
        ,false,
                0,
                false
        )
        questions.add(question)

//        Adding 7th Question
        options = ArrayList<String>()
        options.add("0")
        options.add("Value less than 0")
        options.add("Value greater than 0")
        options.add("compareTo() returns boolean")
        question = Question(7,
                "What is the value returned by function compareTo() if the invoking string is less than the string compared?",
                false,
                0,
                options,
                2,
                "  compareTo() function returns zero when both the strings are equal, it returns a value less than zero if the invoking string is less than the other string being compared and value greater than zero when invoking string is greater than the string compared to.",
                false,
                0,
                false
        )
        questions.add(question)

//        Adding 8th Question
        options = ArrayList<String>()
        options.add("2 2")
        options.add("Compilation Error")
        options.add("3 3")
        options.add("Runtime Error")
        question = Question(8,
                "What will be the output of the following Java code?",
                true,
                R.drawable.ques8,
                options,
                2,
                "None.",
                false,
                0,
                false
        )
        questions.add(question)

//        Adding 9th Question
        options = ArrayList<String>()
        options.add("Byte")
        options.add("String")
        options.add("Array")
        options.add("Class")
        question = Question(9,
                "Which of these classes is not included in java.lang?",
                false,
                0,
                options,
                3,
                "Array class is a member of java.util.",
                false,
                0,
                false
        )
        questions.add(question)

//        Adding 10th Question
        options = ArrayList<String>()
        options.add("File")
        options.add("Writer")
        options.add("InputStream")
        options.add("Reader")
        question = Question(10,
                "Which of these class is not related to input and output stream in terms of functioning?",
                false,
                0,
                options,
                1,
                "A File describes properties of a file, a File object is used to obtain or manipulate the information associated with a disk file, such as the permissions, time date, and directories path, and to navigate subdirectories.",
                false,
                0,
                false
        )
        questions.add(question)

        return questions
    }
}
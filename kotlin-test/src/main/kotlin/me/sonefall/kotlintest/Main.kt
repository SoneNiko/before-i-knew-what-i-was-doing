package me.sonefall.kotlintest

import mu.KotlinLogging
import mu.withLoggingContext
import java.util.logging.LogManager
import java.util.logging.Logger


fun main(args: Array<String>) {

    println("Hello from kotlin")
    print("I can print any number for example ")
    println(100)

    println("The file seems to have an implied class that is the file name with a 'Kt' on the end of it")

    println("====================")


    var nonFinal = "This seems to be a variable. Focus on the fact that it can apparently be modified"
    nonFinal = "like this"
    val immutable = "This is a value. It is not changeable"


    println("====================")


    val runnable = Runnable {
        println("This is code that is executed from within a runnable")
        println(nonFinal)
        println(immutable)
        println("Both variables seem to be perfectly accessible")
    }

    runnable.run()

    runnable.run {
        println("Is this a shortform?")
    }

    run {
        println("It indeed is")
    }

    val thisStuff = run {
        Thread.sleep(1000)
        "Return types seem to look like this"
    }

    println(thisStuff)
    println("This means that this is not async but sequential")

    println("=================")


    val iterator = args.iterator()

    while (iterator.hasNext()) {
        val value = iterator.next()

        println(value)
    }

    // functional programming in java seems to translate in this way, but only if the assignment of the iteration
    // variable is in the next line
    iterator.forEach {
            _: String -> println("This does the same")
    }
    // unused variables are aparently an underscore. Intriguing


    //from now on i will use this format
    args.forEach { s: String ->
        println(s)
        println("What if i print other stuff as well")
    }

    // this is a possible way of doing a list, however it is recommending i move the init function outside of the body like this:
    var testList = List(
        10,
        { index -> index }
    )

    testList = List(
        10
    ) { index -> index }


    println("=================")

    println(testList.size)
    println(testList.get(5).toString() + " Appears to be the same as " + testList[5])
    // but it is recommended I replace it with indexing probably because of its O(1) time complexity???
    // this also means that string concatenation with other variables seems to work just like in java
    println(testList[0]) // bet this works like in java. Yes it does.

    // Now if it try to change things, what happens.
    //    testList[4] = 5;
    // doesnt even work

    var mutableList = mutableListOf("Lorem", "Ipsum", "dolor", "sit", "amet", "consectutor", "adisciping", "elit!")

    println(mutableList) // are these printable? Yes!!
    // I assume these to work on read only lists as well

    println(mutableList.toString()) // careful, IDE will not show redundant stuff

    for (i in mutableList.indices) {
        mutableList[i] = listOf("Lorem", "Ipsum", "dolor", "sit", "amet", "consectutor", "adisciping", "elit!")[7-i]
    }

    for (s in mutableList) {
        print(s + " ")
    }
    println()

    println("=====================")
    println("=====================")
    println("=====================")
    println("=====================")

    println("String concatenation:")

    println("The sizes of the lists we have created are as follows ${mutableList.size}, ${testList.size}")
    println("These are the element that are present in on of the lists \n $mutableList")


    //    error("I can even run errors and they seem to terminate the process. The default exception seems to be IllegalStateException")

    // Im used to LaTeX

    // I wanna try logging


    println(System.getProperty("user.dir"))

//    LogManager.getLogManager().readConfiguration(
//        Thread.currentThread().contextClassLoader.getResourceAsStream("logging.properties")
//    )
//
//    val logger1 = Logger.getLogger("mokisfde")
//
//    logger1.info("Sure enough this is simple")
//    logger1.warning("And supports all the stuff")
//    logger1.severe("Even errors")

    // THis is brilliant for learning new things about stuff you have been using.
    // apparently JUL logs to System.err
    // I will use a different logging method
    // in java the popular method is to use slf4j as a facade for underlying projects

    // Courtesy of Baeldung <3:

    // Apparently logback uses xml

    val logger = KotlinLogging.logger { } //idk what this is for

    // The reason for using curly brackets is that big functions in the log outputs wont be evaluated.
    // Was never a problem for me but actually pretty smart

    logger.trace { "This is a trace log" }
    logger.debug { "This seems to take in functions as arguments" }
    logger.info { "Which all return a string" }
    logger.warn { "WOOOOW so functional. I bet this was a JS kids idea" }
    logger.error { "Interestingly this is called error instead of severe or fatal" }

    withLoggingContext("user" to "Nikolas") {
        withLoggingContext("user" to "Test") {
            logger.info { "What does logging context do?" }
        }
        logger.info { "What does logging context do?" }
    }


}


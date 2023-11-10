package com.example.shemajamebeli2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.example.shemajamebeli2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val textLists = mutableListOf<Text>()
    private val groupedEntries = mutableListOf<MutableList<Text>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


       binding.btnSave.setOnClickListener{
            val userInput = binding.etAnagram.text.toString()
            if(userInput.isNotBlank()){
                val input = Text(userInput)
                textLists.add(input)
                binding.etAnagram.text?.clear()
                 wordsInList()

            }
        }

        binding.btnOutput.setOnClickListener{
            showEnteredWords()
            showGroupedWords()
            listsInList()

        }
    }
   private fun wordsInList (){
       groupedEntries.clear()

       for (text in textLists){
            val size = text.text.length

           val sameWords = textLists.filter {
               it.text.length == size && it.text.toCharArray().sorted() == text.text.toCharArray().sorted()
           }
           if (sameWords.isNotEmpty()){
               groupedEntries.add(sameWords.toMutableList())
           }
       }

   }

    private fun showGroupedWords() {
        val outputText = buildString {
            append("\n\nGrouped Words: \n")
            for (group in groupedEntries) {
                append("Group: ${group.joinToString(", ")}\n")
            }
        }
        binding.tvCount.append(outputText)
    }

    private fun showEnteredWords() {

        val outputText = buildString {
            append("Entered Words: ${textLists.joinToString(", ")}")
        }
       binding.tvCount.text = outputText
    }

    private fun listsInList() {
        val distinctLists = groupedEntries.distinct().size
        binding.tvCount.append("\nNumber of Lists: $distinctLists\n")
    }


    data class Text(val text:String){

    }
}
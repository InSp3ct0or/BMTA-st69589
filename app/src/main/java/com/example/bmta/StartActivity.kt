package com.example.bmta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmta.databinding.ActivityStartBinding


class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up start button click listener
        binding.startBtn.setOnClickListener {
            val heroName = binding.heroNameEdit.text.toString()
            if (heroName.isEmpty()) {
                Toast.makeText(this, "Please enter a hero name.", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("heroName", heroName)
                startActivity(intent)
            }
        }
    }
}

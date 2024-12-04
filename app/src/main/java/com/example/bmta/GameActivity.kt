package com.example.bmta

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmta.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация ViewBinding
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получение имени героя из Intent
        val heroName = intent.getStringExtra("heroName") ?: "Unknown Hero"
        binding.textHeroName.text = heroName

        // Пример статической информации
        binding.textScore.text = "Score: 0"
        binding.textHealth.text = "Health: 100"
        binding.textAttack.text = "Attack: 10"
        binding.textDefense.text = "Defense: 5"
        binding.textHealing.text = "Healing: 2"
        binding.textKills.text = "Kills: 0"

        // Подготовка окружения (просто пример, изображения можно заменить)
        binding.imageNorthWest.setImageResource(R.drawable.nw)
        binding.imageNorth.setImageResource(R.drawable.n)
        binding.imageNorthEast.setImageResource(R.drawable.ne)
        binding.imageWest.setImageResource(R.drawable.w)
        binding.imageEast.setImageResource(R.drawable.e)
        binding.imageSouthWest.setImageResource(R.drawable.sw)
        binding.imageSouth.setImageResource(R.drawable.s)
        binding.imageSouthEast.setImageResource(R.drawable.se)
    }
}

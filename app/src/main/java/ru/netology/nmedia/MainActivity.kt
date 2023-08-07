package ru.netology.nmedia

import Post
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentLikeNumber = 10
    private var currentSharingNumber = 5

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология: Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен – http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
        )

        //преобразование формата представления кол-ва
        fun formatAmount(amount: Int): String {
            return when {
                amount < 1_000 -> amount.toString()
                amount < 10_000 -> "${amount / 1_000}.${(amount % 1_000) / 100}K"
                amount < 1_000_000 -> "${amount / 1_000}K"
                else -> "${amount / 1_000_000}.${(amount % 1_000_000) / 100_000}M"
            }
        }

        // функция увеличения счетчика
        fun increaseLikeNumber() {
            currentLikeNumber++
            binding.likeNumber.text = formatAmount(currentLikeNumber)
        }

        //функция понижения счетчика
        fun backToPrimalStateLikeNumber() {
            if (currentLikeNumber > 0) {
                currentLikeNumber--
                binding.likeNumber.text = formatAmount(currentLikeNumber)
            }
        }

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            binding.root.setOnClickListener {
                Log.d("constraint layout","Сработано!")
                Toast.makeText(this@MainActivity, "сработано", Toast.LENGTH_SHORT).show()
            }

            if (post.likedByMe) {
                likeIcon.setImageResource(R.drawable.liked_icon)
            }
            //обработка кнопки "лайк"
            likeIcon.setOnClickListener {
                Log.d("likeIcon button","Сработано!")
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) increaseLikeNumber() else backToPrimalStateLikeNumber()
                likeIcon.setImageResource(
                    if (post.likedByMe) R.drawable.liked_icon else R.drawable.like_icon
                )
            }
//            //обработка кнопки "поделиться"
//            sharingIcon.setOnClickListener {
//                currentSharingNumber++
//                binding.sharingsNumber.text = formatAmount(currentSharingNumber)
//            }
        }
    }
}
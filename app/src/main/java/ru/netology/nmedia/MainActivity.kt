package ru.netology.nmedia

import Post
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
            likes = 10,
            sharings = 5
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            binding.root.setOnClickListener {
                Log.d("constraint layout","ConstraintLayout")
                Toast.makeText(this@MainActivity, "ConstraintLayout", Toast.LENGTH_SHORT).show()
            }

            binding.avatar.setOnClickListener{}

            if (post.likedByMe) {
                likeIcon.setImageResource(R.drawable.liked_icon)
            }
            //обработка кнопки "лайк"
            likeIcon.setOnClickListener {
                Log.d("likeIcon button","Лайки")
                post.likedByMe = !post.likedByMe
                binding.likeNumber.text = if (post.likedByMe) formatAmount(++post.likes) else formatAmount(--post.likes)
                likeIcon.setImageResource(
                    if (post.likedByMe) R.drawable.liked_icon else R.drawable.like_icon
                )
            }
            //обработка кнопки "поделиться"
            sharingIcon.setOnClickListener {
                post.sharings++
                binding.sharingsNumber.text = formatAmount(post.sharings)
            }
        }
    }
}
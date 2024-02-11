package com.atom.shop.ui.register

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.atom.shop.App
import com.atom.shop.R
import com.atom.shop.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val app = requireActivity().application as? App
        app?.appComponent?.inject(this)

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        setupListeners()
        binding.btnRegister.setOnClickListener {
            if (isValidate()) {
                Toast.makeText(requireContext(), "validated", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }


    private fun isValidate(): Boolean =
        validateFirstUserName() && validateLastName() && validateNumber()

    private fun setupListeners() {
        binding.editFirstName.addTextChangedListener(TextFieldValidation(binding.editFirstName))
        binding.editLastName.addTextChangedListener(TextFieldValidation(binding.editLastName))
        binding.editnumber.addTextChangedListener(TextFieldValidation(binding.editnumber))
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            when (view.id) {
                R.id.edit_first_name -> {
                    validateFirstUserName()
                }

                R.id.edit_last_name -> {
                    validateLastName()
                }

                R.id.editnumber -> {
                    validateNumber()
                }

            }
        }


    }


    /**
     * 1) Для ввода доступны любые символы, но для каждого символа выполняется валидация
     * 2) Введенное значение считается валидным, если оно состоит только из букв кириллицы
     * 3) Пробел и прочие символы, а также латиница, считаются невалидными.
     * 4) Если в поле введен символ, который не удовлетворяет условиям валидации, то все поле выделяется красной подсветкой.
     * 5) Сразу после удаления последнего невалидного символа красная подсветка исчезает.
     * 6) При наличии в поле символа справа отображается иконка крестика. При клике на нее введенное в поле значение удаляется
     */
    private fun validateFirstUserName(): Boolean {
        val userName = binding.editFirstName.text.toString().trim()
        if (userName.isEmpty()) {
            binding.inputFirstNameContainer.error = "Required Field!"
            binding.editFirstName.requestFocus()
            return false
        } else {
            for (char in userName) {
                if (!char.isWhitespace() && !char.isLetter() || char !in 'А'..'Я' && char !in 'а'..'я' && char != 'ё' && char != 'Ё') {
                    binding.inputFirstNameContainer.error = "Для имени доступна только кириллица!"
                    return false
                }
            }
        }
        binding.inputFirstNameContainer.isErrorEnabled = false
        return true
    }

    /**
     * 1) Для ввода доступны любые символы, но для каждого символа выполняется валидация
     * 2) Введенное значение считается валидным, если оно состоит только из букв кириллицы
     * 3) Пробел и прочие символы, а также латиница, считаются невалидными.
     * 4) Если в поле введен символ, который не удовлетворяет условиям валидации, то все поле выделяется красной подсветкой.
     * 5) Сразу после удаления последнего невалидного символа красная подсветка исчезает.
     * 6) При наличии в поле символа справа отображается иконка крестика. При клике на нее введенное в поле значение удаляется
     */
    private fun validateLastName(): Boolean {
        val lastName = binding.editLastName.text.toString().trim()
        if (lastName.isEmpty()) {
            binding.inputLastNameContainer.error = "Поле ввода пустое"
            binding.editLastName.requestFocus()
            return false
        } else {
            for (char in lastName) {
                if (!char.isWhitespace() && !char.isLetter() || char !in 'А'..'Я' && char !in 'а'..'я' && char != 'Ђ') {
                    binding.inputLastNameContainer.error = "Для имени доступна только кириллица!"
                    return false
                }
            }
        }
        binding.inputLastNameContainer.isErrorEnabled = false
        return true
    }

    /**
     * 1) Ввод осуществляется по маске “+7 ХХХ ХХХ-ХХ-ХХ"
     * 2) Любые символы, отличные от цифр, игнорируются и не вводятся
     * 3) Если первая введенная цифра “7”, она игнорируется (т.к. “+7” согласно маске автоматически подставляется), ввод начинается со второй цифры.
     * 4) При наличии в поле символа справа отображается иконка крестика. При клике на нее введенное в поле значение удаляется
     */
    private fun validateNumber(): Boolean {
        val phoneNumber = binding.editnumber.text.toString().trim()
        return if (phoneNumber.isEmpty()) {
            binding.inputPhoneContainer.error = "Поле ввода пустое"
            binding.editnumber.requestFocus()
            false
        } else {
            if (!phoneNumber.matches(Regex("^\\+7 (\\d{3} )?\\d{3}-\\d{2}-\\d{2}$"))) {
                binding.inputPhoneContainer.error =
                    "Invalid phone number format! Please use +7 XXX XXX-XX-XX format."
                false
            } else {
                binding.inputPhoneContainer.isErrorEnabled = false
                true
            }
        }
    }
}
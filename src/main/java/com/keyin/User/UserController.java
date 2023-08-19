package com.keyin.User;

import com.keyin.BinaryTree.BinarySearchTree;
import com.keyin.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/enter-numbers")
    public String showUserInputPage() {
        return "enter-numbers";
    }

    @GetMapping("/")
    public ModelAndView showHomePage() {
        return new ModelAndView("index");
    }

    @PostMapping("/process-numbers")
    public String processUserInput(@RequestParam("numbers") String numbers, Model model) {
        processAndSaveUserInput(numbers);
        return "process-numbers";
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees(Model model) {
        List<User> userInputList = userRepository.findAll();
        List<String> treeJsonList = constructTreeJsonList(userInputList);
        model.addAttribute("treeJsonList", treeJsonList);
        return "previous-trees";
    }

    private void processAndSaveUserInput(String numbers) {
        BinarySearchTree bst = constructBinarySearchTree(numbers);
        String treeJson = bst.toJson();

        User user = new User(numbers);
        user.setTree(treeJson);
        
        try {
            userRepository.save(user);
            System.out.println("User saved successfully!");
        } catch (Exception e) {
            System.out.println("Couldn't save.");
        }
    }

    private List<String> constructTreeJsonList(List<User> userList) {
        List<String> treeJsonList = new ArrayList<>();
        for (User user : userList) {
            BinarySearchTree bst = constructBinarySearchTree(user.getInput());
            treeJsonList.add(bst.toJson());
        }
        return treeJsonList;
    }

    private BinarySearchTree constructBinarySearchTree(String numbers) {
        BinarySearchTree bst = new BinarySearchTree();
        String[] numberArray = numbers.split("\\s+");
        for (String number : numberArray) {
            try {
                int value = Integer.parseInt(number);
                bst.insert(value);
            } catch (NumberFormatException e) {
            }
        }
        return bst;
    }
}

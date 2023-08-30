package com.gonggubox.controller.item;

import com.gonggubox.dto.item.CategoryDto;
import com.gonggubox.service.item.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto.CategoryPostDto categoryPostDto) {
        return ResponseEntity.ok().body(categoryService.addCategory(categoryPostDto));
    }

    @GetMapping("/getCategory")
    public ResponseEntity<?> getCategory(@RequestParam Long categoryId) {
        return ResponseEntity.ok().body(categoryService.getCategory(categoryId));
    }

//    @GetMapping("/getCategoryTree")
//    public ResponseEntity<?> getCategoryTree() {
//        return ResponseEntity.ok().body(categoryService.getCategoryTree());
//    }

    @PatchMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto.CategoryPatchDto categoryPatchDto) {
        return ResponseEntity.ok().body(categoryService.updateCategory(categoryPatchDto));
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<?> deleteCategory(@RequestParam Long categoryId) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(categoryId));
    }
}

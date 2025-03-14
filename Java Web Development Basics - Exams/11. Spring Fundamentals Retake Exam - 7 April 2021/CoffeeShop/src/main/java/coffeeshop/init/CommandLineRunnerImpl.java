package coffeeshop.init;

import coffeeshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initCategories();
    }
}

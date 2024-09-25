package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Locale;

public class Wrappers {
    public static void enterTextWrapper(WebDriver driver, By locator, String textToEnter) {
        System.out.println("Sending keys");
        Boolean success;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement inputBox = driver.findElement(locator);
            inputBox.clear();
            inputBox.sendKeys(textToEnter);
            inputBox.sendKeys(Keys.ENTER);
            success = true;
        } catch (Exception e) {
            System.out.println("Exception Occured! " + e.getMessage());
            success = false;
        }
    }

    public static void clickOnElementWrapper(WebDriver driver, By locator) {
        System.out.println("Clicking");
        Boolean success;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement clickableElement = driver.findElement(locator);
            clickableElement.click();
            Thread.sleep(4000);
            success = true;
        } catch (Exception e) {
            System.out.println("Exception Occured! " + e.getMessage());
            success = false;
        }
    }

    public static boolean searchStarRatingAndPrintCount(WebDriver driver, By locator, double starRating) {
        int washingMachineCount = 0;
        boolean success;
        try {
            List<WebElement> starRatingElements = driver.findElements(locator);
            for (WebElement starRatingElement : starRatingElements) {
                if (Double.parseDouble(starRatingElement.getText()) <= starRating) {
                    washingMachineCount++;

                }
            }
            System.out.println("Count of washing machine which has star rating is less than or Equal to" + starRating
                    + ":" + washingMachineCount);
            success = true;

        } catch (Exception e) {
            System.out.println("Exception Occured! " + e.getMessage());
            success = false;
        }
        return success;

    }

    public static Boolean printTitleAndDiscountIphone(WebDriver driver, By locator, int discount) {
        Boolean success;
        try {
            // Get all the product rows using the provided locator
            List<WebElement> productRows = driver.findElements(locator);

            // Create a map to store the iPhone title and discount percentage
            HashMap<String, String> iphoneTitleDiscountMap = new HashMap<>();

            // Loop through each product row
            for (WebElement productRow : productRows) {
                // Find the discount element within the current product row
                WebElement discountElement = productRow.findElement(By.xpath(".//div[contains(@class,'yKfJKb')]"));

                // Extract the discount percentage from the discount element
                String discountText = discountElement.getText();
                int discountValue = Integer.parseInt(discountText.replaceAll("[^\\d]", ""));

                // If the discount is greater than the specified threshold, add it to the map
                if (discountValue > discount) {
                    // Get the iPhone title from the current product row
                    String iphoneTitle = productRow.findElement(By.xpath(".//div[@class='KzDlHz']")).getText();
                    iphoneTitleDiscountMap.put(discountText, iphoneTitle);
                }
            }

            // Print the iPhone titles and their corresponding discounts
            for (Map.Entry<String, String> iphoneTitleDiscounts : iphoneTitleDiscountMap.entrySet()) {
                System.out.println("iPhone Discount Percentage: " + iphoneTitleDiscounts.getKey()
                        + " and its title: " + iphoneTitleDiscounts.getValue());
            }
            success = true;

        } catch (Exception e) {
            System.out.println("Exception Occurred! " + e.getMessage());
            success = false;
        }
        return success;
    }

    public static boolean printTitleAndImageUrlOfCoffeeMug(WebDriver driver, By locator) {
        boolean success;
        try {
            List<WebElement> userReviewElements = driver.findElements(locator);
            Set<Integer> userReviewSet = new HashSet<>();
            // HashSet<String> userReviewSet = new HashSet<>();

            for (WebElement userReviewElement : userReviewElements) {
                int userReview = Integer.parseInt(userReviewElement.getText().replaceAll("[^\\d]", ""));
                userReviewSet.add(userReview);

            }
            List<Integer> userReviewCountList = new ArrayList<>(userReviewSet);
            Collections.sort(userReviewCountList, Collections.reverseOrder());
            System.out.println(userReviewCountList);
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

            LinkedHashMap<String, String> productDetailMap = new LinkedHashMap<>();
            for (int i = 0; i < 5; i++) {
                String formattedUserReviewCount = "(" + numberFormat.format(userReviewCountList.get(i)) + ")";
                String productTitle = driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'"
                        + formattedUserReviewCount + "')]/../../a[@class='wjcEIp']")).getText();
                String productImgURL = driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'"
                        + formattedUserReviewCount + "')]/../..//img[@class='DByuf4']")).getAttribute("src");
                String highesrReviewCountAndProductTitle = String.valueOf(i + 1) + "highest Review COunt:"
                        + formattedUserReviewCount + "Title: " + productTitle;
                productDetailMap.put(highesrReviewCountAndProductTitle, productImgURL);
            }

            for (Map.Entry<String, String> productDetails : productDetailMap.entrySet()) {
                System.out.println(productDetails.getKey() + "Product Image URL: " + productDetails.getValue());
            }
            success = true;
        } catch (Exception e) {
            System.out.println("Exception Occured! " + e.getMessage());
            success = false;
        }
        return success;
    }

    // public Boolean printProductAndDiscount(WebDriver driver, int
    // discountThreshold) {
    // // Find product names and discounts
    // Boolean status = false;
    // // Find product names and discounts
    // try {
    // List<WebElement> products = driver.findElements(By.xpath(
    // "//div[@class='UkUFwK']/../../../preceding-sibling::div[@class='col
    // col-7-12']/div[@class='KzDlHZ']"));
    // List<WebElement> discounts =
    // driver.findElements(By.xpath("//div[@class='UkUFwK']/span"));

    // // iterate through products and print titles with their discounts
    // // int minSize = Math.min(products.size(), discounts.size());
    // for (int i = 0; i < discounts.size(); i++) {
    // String productName = products.get(i).getText();
    // String discountText = discounts.get(i).getText();
    // int discountValue = Integer.parseInt(discountText.replaceAll("[^\\d]",
    // "").trim());
    // System.out.println("discount value : " + discountValue);

    // // check if discountText is valid and contains a percentage symbol
    // if (discountText != null && !discountText.isEmpty() &&
    // discountText.contains("%")
    // && discountValue >= 15) {
    // System.out.println("Product: " + productName + " | Discount: " +
    // discountText);
    // status = true;
    // // }else{
    // // System.out.println("All Discount value is less than 17");
    // // status = true;
    // // }
    // }

    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return status;
    // }

    public static Boolean validateSearchInput(WebDriver driver) {
        Boolean status;
        WebElement searchBox = driver.findElement(By.xpath("//input[@class='zDPmFV']"));
        status = searchBox.getAttribute("value").contains("iphone");
        return status;
    }

    public static Boolean printProductAndDiscount(WebDriver driver, int discountThreshold) {
        // Find product names and discounts
        Boolean status = false;
        // Find product names and discounts
        try {
            List<WebElement> products = driver.findElements(By.xpath(
                    "//div[@class='UkUFwK']/../../../preceding-sibling::div[@class='col col-7-12']/div[@class='KzDlHZ']"));
            List<WebElement> discounts = driver.findElements(By.xpath("//div[@class='UkUFwK']/span"));

            // iterate through products and print titles with their discounts
            // int minSize = Math.min(products.size(), discounts.size());
            for (int i = 0; i < discounts.size(); i++) {
                String productName = products.get(i).getText();
                String discountText = discounts.get(i).getText();
                int discountValue = Integer.parseInt(discountText.replaceAll("[^\\d]", "").trim());
                System.out.println("discount value : " + discountValue);

                // check if discountText is valid and contains a percentage symbol
                if (discountText != null && !discountText.isEmpty() && discountText.contains("%")
                        && discountValue >= 15) {
                    System.out.println("Product: " + productName + " | Discount: " + discountText);
                    status = true;
                    // }else{
                    // System.out.println("All Discount value is less than 17");
                    // status = true;
                }
            }

     
        } catch(Exception e) {
            e.printStackTrace();
            }
            return status;
    }

    // public static Boolean printTopFiveProductTitleAndImageURLWithHighestNumberOfRating(WebDriver driver, By locator) {
    //     Boolean status = false;
    //     try {
    //         List<WebElement> poductCard = driver.findElements(locator);
    //         // //div[@class='slAVV4']
    //         // here created a class Product as collection take object as input too,
    //         // so we can access the required data with getters.
    //         List<Product> productList = new ArrayList<>();
    //         // iterate through product card to get title, url and review number
    //         for (WebElement product : poductCard) {
    //             String title = product.findElement(By.xpath(".//a[@class='wjcEIp']")).getText();
    //             String imageurl = product.findElement(By.xpath("./a[@class='VJA3rP']//img")).getAttribute("src");
    //             String reviewNumber = product
    //                     .findElement(By.xpath(".//div[@class='_5OesEi afFzxY']//span[@class='Wphh3N']")).getText();
    //             int numberOfReviews = Integer.valueOf(reviewNumber.replaceAll("[^\\d]", ""));
    //             productList.add(new Product(title, imageurl, numberOfReviews));
    //         }
    //         Collections.sort(productList, Collections.reverseOrder());

    //         // Print the top 5 products' titles and image URLs
    //         for (int i = 0; i < Math.min(5, productList.size()); i++) {
    //             Product topProduct = productList.get(i);
    //             System.out.println("title : " + topProduct.getTitle() + "  " + "image url : " + topProduct.getImageUrl()
    //                     + " " + "reviws : " + topProduct.getNumberOfReviews());
    //             status = true;
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         status = false;
    //     }
    //     return status;
    // }
 }


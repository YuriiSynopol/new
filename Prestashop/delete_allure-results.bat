del /q "D:\Presta\Prestashop\allure-results\*"
FOR /D %%p IN ("D:\Presta\Prestashop\allure-results\*.*") DO rmdir "%%p" /s /q
exit
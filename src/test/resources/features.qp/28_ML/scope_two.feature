# language: ru
@tag
@tag1
@tag2
Функция: Проверка BLW_DM_MINUS_PARAM

  Предыстория: по сути тут разные должны быть тесты, но столько текста обезличивать я не хочу)))
    Дано Подключаемся к базе db-name с логином "aaaa" и паролем "ssss"
    И Удаляем правила для системы "system"

  @tag3
  Сценарий: Проверка работы переводов по правилам с "-param"
    Тогда Настраиваем таблицу tablename:
      | code  | description     | defaultStatus | systemId | synchMode | step |
      | Param | New Param Param | A             | Param    | SYNCH     | SEND |
    Также Настраиваем таблицу tablename2:
      | scenario | ruleOrder | rule  |
      | Param    | 1         | param |
    И Настраиваем таблицу tablename3:
      | rule  | object | pname | pvalue                 |
      | Param |        | Param | -Param;                |
      | Param |        | Param | -Param                 |
      | Param |        | Param | -Param                 |
      | Param | Param  | Param | -Param                 |
      | Param | Param  | Param | Param                  |
      | Param |        | Param | Заблокирован Param     |
      | Param | Param  | Param | Param                  |
      | Param | Param  | Param | Param                  |
      | Param | Param  | Param | Param о Param операции |
      | Param | Param  | Param | $email                 |
      | Param | Param  | Param | $message               |

    Затем Отправить перевод из Внутри сети используя шаблон "/first_systems/json1.json" :
      | param    | Param             |
      | trOption | 8                 |
      | param    | Param Payer Param |
      | param    | Param             |
      | param    | Param             |
      | param    | +Param            |
      | param    | Param             |
      | param    | Param             |
      | param    | Param             |
      | param    | Param             |
    И Проверяем, что перевод с uin: "27001001" НЕ ФРОД

    И Отправить перевод из ТС используя шаблон "/second_system/json2.json" :
      | param     | Param             |
      | aprCityId | Param             |
      | param     | Param Payer Param |
      | param     | Param             |
      | param     | Param             |
      | param     | +Param            |
      | param     | Param             |
      | param     | Param             |
      | param     | Param             |
      | param     | Param             |
    И Проверяем, что перевод с uin: "27001002" НЕ ФРОД

    И Отправить перевод из param используя шаблон "/second_system/json3.json" :
      | param | param             |
      | param | param             |
      | param | param Payer param |
      | param | param             |
      | param | param66           |
      | param | +79990000000      |
      | param | param             |
      | param | param             |
      | param | param             |
      | param | param             |
    И Проверяем, что перевод с uin: "param" НЕ ФРОД

    И Отправить перевод из Торговой сети для погашения кредита используя шаблон "/second_system/json4.json" :
      | param     | 27001004          |
      | serviceId | 7777              |
      | requisite | 99999526623343433 |
      | param     | param Payer param |
      | param     | param             |
      | param     | param66           |
      | param     | +79990000000      |
      | param     | param             |
      | param     | param             |
      | param     | param             |
      | param     | param             |
    И Проверяем, что перевод с uin: "27001004" НЕ ФРОД

    И Отправить перевод из ТС используя шаблон "/second_system/json5.json" :
      | param | 27001005          |
      | param | param Payer param |
      | param | param             |
      | param | param66           |
      | param | +param            |
      | param | param             |
      | param | param             |
      | param | param             |
      | param | param             |
    И Проверяем, что перевод с uin: "27001005" ФРОД по reason:
      | param |

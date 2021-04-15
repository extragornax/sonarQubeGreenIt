/**
 * @fileoverview Remplacer les $i++ par ++$i
 * @author Gael Pellevoizin
 */
"use strict";

//------------------------------------------------------------------------------
// Requirements
//------------------------------------------------------------------------------

var rule = require("../../../lib/rules/s67"),

    RuleTester = require("eslint").RuleTester;


//------------------------------------------------------------------------------
// Tests
//------------------------------------------------------------------------------

var ruleTester = new RuleTester();
ruleTester.run("s67", rule, {

    valid: [

        // give me some code that won't trigger a warning
    ],

    invalid: [
        {
            code: "counter++;",
            errors: [{
                message: "Fill me in.",
                type: "Me too"
            }]
        }
    ]
});

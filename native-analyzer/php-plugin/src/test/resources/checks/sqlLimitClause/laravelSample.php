<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\DB;

class UserController extends Controller
{


    public function listUserByOrNameAndVotes(){

    $users = DB::table('users') // NOK
            ->where('votes', '>', 100)
            ->orWhere(function($query) {
                $query->where('name', 'Abigail')
                      ->where('votes', '>', 50);
            })
            ->get();
            return $users;
        }


    /**
     * Show a list of all of the application's users.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $users = DB::table('users')  // NOK
                 ->get();
        return $users;
       // return view('user.index', ['users' => $users]);
    }

    /**
     * Show a list of all of the application's users.
     ** @param $age
     ** @param $votes
     * @return \Illuminate\Http\Response
     */
    public function listUserByVoteAndAge()
    {
    $users = DB::table('users') // NOK
                ->where('votes', '=', $votes)
                ->where('age', '>', $age)
                ->get();

    return $users;
    }



    public function topArticles(){

    $top_articles = DB::table('an_pages')
                    ->where('status',1)
                    ->limit(30)
                    ->offset(0)
                    ->orderBy('id', 'DESC')
                    ->get();
        }
}

?>
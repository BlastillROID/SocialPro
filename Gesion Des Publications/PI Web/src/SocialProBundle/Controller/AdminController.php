<?php

namespace SocialProBundle\Controller;

use SocialProBundle\Form\RechercheType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use SocialProBundle\Entity\Posts;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;

class AdminController extends Controller
{

    public function showUsersAction(){
        $userManager = $this->get('fos_user.user_manager');
        $users = $userManager->findUsers();
        return $this->render("SocialProBundle:Admin:userlist.html.twig", ['users' => $users]);
    }

    public function showPostsAction(){
        $em = $this->getDoctrine()->getManager();
        $posts = $em->getRepository("SocialProBundle:Posts")->findAll();
        return $this->render("SocialProBundle:Admin:postlist.html.twig", ['posts' => $posts]);
    }

    public function deleteAction($id){
        $em = $this->getDoctrine()->getManager();

        $modele = $em->getRepository("SocialProBundle:Posts")->find($id);
        $em->remove($modele);
        $em->flush();

        return $this->redirectToRoute('admin_show_postlist');
    }

    public function filtrerAction(){
        $em = $this->getDoctrine()->getManager();
        $em->getRepository('SocialProBundle:Posts')->filterSignalDQL();
        return $this->redirectToRoute('admin_show_postlist');
    }

    public function rechercheUsernameDQLAction(Request $request)
    {
        $post = new Posts();
        $em = $this->getDoctrine()->getManager();
        $posts=$em->getRepository('SocialProBundle:Posts')->findAll();

        $Form = $this->createForm(RechercheType::class,$post);

        $Form->handleRequest($request);
        if($Form->isValid())
        {
            $text=$post->getPostContent();
            $posts=$em->getRepository("SocialProBundle:Posts")->findUsernameDQL($text);
        }
        return $this->render("SocialProBundle:Admin:recherche.html.twig",
            array('Form'=>$Form->createView(),'posts'=>$posts));

    }

}
